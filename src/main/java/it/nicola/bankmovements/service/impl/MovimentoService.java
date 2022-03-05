package it.nicola.bankmovements.service.impl;

import it.nicola.bankmovements.entity.ImportazioneEntity;
import it.nicola.bankmovements.entity.MovimentoEntity;
import it.nicola.bankmovements.excel.resolver.*;
import it.nicola.bankmovements.model.FiltriMovimenti;
import it.nicola.bankmovements.repository.MovimentoRepository;
import it.nicola.bankmovements.utils.DateUtils;
import it.nicola.bankmovements.xls.XLSModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

@Service
@Slf4j
public class MovimentoService {

    private static final String CSV_SEPARATOR = ";";
    private static HashMap<String, DynamicAutocompleteMov> dictionaryCodiciABI = new HashMap<>();

    private final MovimentoRepository movimentoRepository;
    private final ImportazioniService importazioniService;
    private final ContatoriService contatoriService;
    private final DominiService dominiService;
    private final DominiSpesaService dominiSpesaService;

    public MovimentoService(MovimentoRepository movimentoRepository, ImportazioniService importazioniService, ContatoriService contatoriService, DominiService dominiService, DominiSpesaService dominiSpesaService) {
        this.movimentoRepository = movimentoRepository;
        this.importazioniService = importazioniService;
        this.contatoriService = contatoriService;
        this.dominiService = dominiService;
        this.dominiSpesaService = dominiSpesaService;

        caricaConfigurazioniAutocomplete();
    }

    private void caricaConfigurazioniAutocomplete(){
        dictionaryCodiciABI.put("18", new ResolverMovimentiGenerici(dominiService));
        dictionaryCodiciABI.put("27", new ResolverMovimentiStipendi(dominiService));
        dictionaryCodiciABI.put("43", new ResolverMovimentiPagamenti(dominiService, dominiSpesaService));
        dictionaryCodiciABI.put("50", new ResolverMovimentiAddebito(dominiService, dominiSpesaService));
        dictionaryCodiciABI.put("60", new ResolverMovimentiGenerici(dominiService));
        dictionaryCodiciABI.put("66", new ResolverMovimentiGenerici(dominiService));
        dictionaryCodiciABI.put("91", new ResolverMovimentiGenerici(dominiService));
    }

    public Page<MovimentoEntity> findAll(FiltriMovimenti filtriMovimenti, Pageable pagination){
        return movimentoRepository.findAll(pagination);
    }

    public List<MovimentoEntity> importMovimentiFromXls() throws Exception{
        FileInputStream in = new FileInputStream(new File("C:\\Users\\nicol\\Desktop\\BankMovement TEST\\XLS import\\Mov-Copia.xls"));
        return processXlsBanksMovement(new HSSFWorkbook(in));
    }

    private List<MovimentoEntity> processXlsBanksMovement(HSSFWorkbook workbook) throws Exception{
        ImportazioneEntity imp = importazioniService.insertImportazioni(createImportazione());
        List<MovimentoEntity> movimenti = new ArrayList<>();

        HSSFSheet sheet =  workbook.getSheetAt(0);

        int index = 9;
        while(StringUtils.hasText(sheet.getRow(index).getCell(1).getStringCellValue())){
            XLSModel row = getMovimentoFromRow(sheet.getRow(index));
            MovimentoEntity movimento = mapXLSModelToMovimento(row, imp.getId());
            movimenti.add(movimento);

            if(dictionaryCodiciABI.containsKey(row.getCausaleABI())) {
                dictionaryCodiciABI.get(row.getCausaleABI()).autocompleteDynamicMovFields(movimento, row);

                log.info(movimento.toString());
            }

            isMovimentoValido(movimento);
            movimentoRepository.insert(movimento);
            index++;
        }

        return movimenti;
    }

    public void saveAllMovimentiEntity(List<MovimentoEntity> movimenti){
        movimentoRepository.saveAll(movimenti);
    }

    private XLSModel getMovimentoFromRow(HSSFRow row) throws Exception {
        DecimalFormat df = new DecimalFormat("0.00");
        XLSModel objectRow = new XLSModel();
        objectRow.setData(DateUtils.getDateFromString(row.getCell(1).getStringCellValue()));
        objectRow.setCausaleABI(row.getCell(3).getStringCellValue());
        objectRow.setDescrizione(row.getCell(4).getStringCellValue());
        objectRow.setImporto(row.getCell(5).getNumericCellValue());

        return objectRow;
    }

    private MovimentoEntity mapXLSModelToMovimento(XLSModel row, Integer nImportazione) throws Exception {
        MovimentoEntity mov = new MovimentoEntity();
        mov.setData(row.getData());
        mov.setImporto(row.getImporto());
        mov.setNImportazione(nImportazione);
        return mov;
    }

    public Long deleteByNImportazione(Integer nImportazione){
        return movimentoRepository.deleteBynImportazione(nImportazione);
    }

    private ImportazioneEntity createImportazione(){
        ImportazioneEntity imp = new ImportazioneEntity();
        imp.setId(contatoriService.next("nImportazione"));
        imp.setDescrizione("Importazione del " + DateUtils.getStringTimeItalian(new Date()));
        imp.setDataImportazione(new Date());

        return imp;
    }

    private void isMovimentoValido(MovimentoEntity mov){
        if(StringUtils.hasText(mov.getCategoria()) && StringUtils.hasText(mov.getDescrizione()) && mov.getImporto() != null && mov.getData() != null)
            mov.setIsValido(true);
        else
            mov.setIsValido(false);
    }

    public List<MovimentoEntity> findByNImportazione(Integer nImportazione){
        return movimentoRepository.findBynImportazione(nImportazione);
    }

    public void generateCSVFromNImportazione(Integer nImportazione) throws IOException {
        List<MovimentoEntity> movimenti = movimentoRepository.findBynImportazione(nImportazione);

        String nomeFile = "Movimenti" + ".csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\nicol\\Desktop\\BankMovement TEST\\CSV output\\"+nomeFile))) {
            writer.append("Data;Descrizione;Importo;Categoria").append(System.lineSeparator());
            movimenti.forEach(mov -> {
                try {
                    writer.append(DateUtils.getStringTimeItalian(mov.getData())).append(CSV_SEPARATOR)
                            .append(mov.getDescrizione()).append(CSV_SEPARATOR)
                            .append(String.valueOf(mov.getImporto())).append(CSV_SEPARATOR)
                            .append(mov.getCategoria()).append(System.lineSeparator());
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            });
        } catch (IOException ex) {
            throw ex;
        }
    }
}
