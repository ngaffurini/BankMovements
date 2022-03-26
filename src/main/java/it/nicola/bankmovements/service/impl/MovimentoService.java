package it.nicola.bankmovements.service.impl;

import it.nicola.bankmovements.dto.MovimentoDto;
import it.nicola.bankmovements.entity.ImportazioneEntity;
import it.nicola.bankmovements.entity.MovimentoEntity;
import it.nicola.bankmovements.excel.resolver.*;
import it.nicola.bankmovements.mapper.MovimentoMapper;
import it.nicola.bankmovements.model.FiltriMovimenti;
import it.nicola.bankmovements.repository.MovimentoRepository;
import it.nicola.bankmovements.utils.DateUtils;
import it.nicola.bankmovements.xls.XLSModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.io.*;
import java.text.ParseException;
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
    private final MovimentoMapper movimentoMapper;

    public MovimentoService(MovimentoRepository movimentoRepository, ImportazioniService importazioniService, ContatoriService contatoriService, DominiService dominiService, MovimentoMapper movimentoMapper) {
        this.movimentoRepository = movimentoRepository;
        this.importazioniService = importazioniService;
        this.contatoriService = contatoriService;
        this.dominiService = dominiService;
        this.movimentoMapper = movimentoMapper;

        caricaConfigurazioniAutocomplete();
    }

    private void caricaConfigurazioniAutocomplete(){
        dictionaryCodiciABI.put("18", new ResolverMovimentiGenerici(dominiService));
        dictionaryCodiciABI.put("27", new ResolverMovimentiStipendi(dominiService));
        dictionaryCodiciABI.put("43", new ResolverMovimentiPagamenti(dominiService));
        dictionaryCodiciABI.put("50", new ResolverMovimentiAddebito(dominiService));
        dictionaryCodiciABI.put("60", new ResolverMovimentiGenerici(dominiService));
        dictionaryCodiciABI.put("66", new ResolverMovimentiTasseConto(dominiService));
        dictionaryCodiciABI.put("91", new ResolverMovimentiGenerici(dominiService));
    }

    public Page<MovimentoDto> findAll(Pageable pagination){
        Page<MovimentoEntity> movimentoEntitiesList = movimentoRepository.findAll(pagination);

        return new PageImpl<>(
                movimentoMapper.toDtos(movimentoEntitiesList.getContent()),
                movimentoEntitiesList.getPageable(),
                movimentoEntitiesList.getTotalElements());
    }

    public Page<MovimentoDto> findAll(FiltriMovimenti filtriMovimenti, Pageable pagination){
        Page<MovimentoEntity> movimentoEntitiesList = movimentoRepository.findAll(pagination);

        return new PageImpl<>(
                movimentoMapper.toDtos(movimentoEntitiesList.getContent()),
                movimentoEntitiesList.getPageable(),
                movimentoEntitiesList.getTotalElements());
    }

    public Boolean importMovimentiFromXls() throws IOException, ParseException {
        try(FileInputStream in = new FileInputStream(new File("C:\\Users\\nicol\\Desktop\\BankMovement TEST\\XLS import\\Movimenti2022.xls"))){
            return processXlsBanksMovement(new HSSFWorkbook(in));
        }
    }

    private Boolean processXlsBanksMovement(HSSFWorkbook workbook) throws ParseException{
        ImportazioneEntity imp = importazioniService.insertImportazioni(createImportazione());

        List<MovimentoDto> movimenti = new ArrayList<>();

        HSSFSheet sheet =  workbook.getSheetAt(0);

        int index = 9;
        while(StringUtils.hasText(sheet.getRow(index).getCell(1).getStringCellValue())){
            XLSModel row = getMovimentoFromRow(sheet.getRow(index));
            MovimentoDto movimento = mapXLSModelToMovimento(row, imp.getId());
            movimenti.add(movimento);

            if(dictionaryCodiciABI.containsKey(row.getCausaleABI())) {
                dictionaryCodiciABI.get(row.getCausaleABI()).autocompleteDynamicMovFields(movimento, row);
            }

            movimento.setValido(isMovimentoValido(movimento));
            index++;
        }

        return this.saveAllMovimentiEntity(movimenti);
    }

    public Boolean saveAllMovimentiEntity(List<MovimentoDto> movimenti){
        movimentoRepository.saveAll(movimentoMapper.toEntities(movimenti));
        return true;
    }

    private XLSModel getMovimentoFromRow(HSSFRow row) throws ParseException {
        XLSModel objectRow = new XLSModel();
        objectRow.setData(DateUtils.getDateFromString(row.getCell(1).getStringCellValue()));
        String codiceAbi = row.getCell(3).getCellType() == CellType.NUMERIC ? String.valueOf((int)row.getCell(3).getNumericCellValue()) : row.getCell(3).getStringCellValue();
        objectRow.setCausaleABI(codiceAbi);
        objectRow.setDescrizione(row.getCell(4).getStringCellValue());
        objectRow.setImporto(row.getCell(5).getNumericCellValue());

        return objectRow;
    }

    private MovimentoDto mapXLSModelToMovimento(XLSModel row, Integer nImportazione) {
        MovimentoDto mov = new MovimentoDto();
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

    private boolean isMovimentoValido(MovimentoDto mov){
        return StringUtils.hasText(mov.getCategoria()) && StringUtils.hasText(mov.getDescrizione()) && mov.getImporto() != null && mov.getData() != null;
    }

    public Page<MovimentoDto> findByNImportazione(Integer nImportazione, PageRequest pagination){
        Page<MovimentoEntity> movimentoEntitiesList = movimentoRepository.findBynImportazioneOrderByValido(nImportazione, pagination);

        return new PageImpl<>(
            movimentoMapper.toDtos(movimentoEntitiesList.getContent()),
            movimentoEntitiesList.getPageable(),
            movimentoEntitiesList.getTotalElements());
    }

    public Page<MovimentoDto> findByValido(Boolean valido, PageRequest pagination){
        pagination.getSort().and(Sort.by("nImportazione").ascending());

        Page<MovimentoEntity> movimentoEntitiesList = movimentoRepository.findByValido(valido, pagination);

        return new PageImpl<>(
          movimentoMapper.toDtos(movimentoEntitiesList.getContent()),
          movimentoEntitiesList.getPageable(),
          movimentoEntitiesList.getTotalElements());
    }

    public Page<MovimentoDto> findByValidoAndNImportazione(Boolean valido, Integer nImportazione, PageRequest pagination){
        Page<MovimentoEntity> movimentoEntitiesList = movimentoRepository.findByValidoAndNImportazione(valido, nImportazione, pagination);

        return new PageImpl<>(
                movimentoMapper.toDtos(movimentoEntitiesList.getContent()),
                movimentoEntitiesList.getPageable(),
                movimentoEntitiesList.getTotalElements());
    }

    public void generateCSVFromNImportazione(Integer nImportazione) throws IOException {
        List<MovimentoDto> movimenti = movimentoMapper.toDtos(movimentoRepository.findBynImportazione(nImportazione));

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
        }
    }
}
