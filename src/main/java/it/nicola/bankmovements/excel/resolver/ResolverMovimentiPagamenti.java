package it.nicola.bankmovements.excel.resolver;

import it.nicola.bankmovements.dto.DominiDto;
import it.nicola.bankmovements.dto.MovimentoDto;
import it.nicola.bankmovements.service.impl.DominiService;
import it.nicola.bankmovements.xls.XLSModel;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ResolverMovimentiPagamenti extends DynamicAutocompleteMov{

    public ResolverMovimentiPagamenti(){}

    public ResolverMovimentiPagamenti(DominiService dominiService){
        super(dominiService);
    }

    @Override
    public void autocompleteDynamicMovFields(MovimentoDto mov, XLSModel xlsModel){
        String descrizione;
        if(xlsModel.getDescrizione().contains("\\ \\")){
            descrizione = xlsModel.getDescrizione().substring(xlsModel.getDescrizione().indexOf("PRESSO ") + 7, xlsModel.getDescrizione().indexOf("\\ \\")).trim();
        }else if(xlsModel.getDescrizione().contains("ESERCENTE")){
            descrizione = xlsModel.getDescrizione().substring(xlsModel.getDescrizione().indexOf("ESERCENTE ") + 10).trim();
        }else{
            descrizione = xlsModel.getDescrizione();
        }

        List<DominiDto> ds = dominiService.getDominiByDescrizioneAndCodiceAbi(descrizione, xlsModel.getCausaleABI());

        if(ds != null && ds.size() == 1) {
            mov.setCategoria(ds.get(0).getCategoria());
        }

        mov.setDescrizione(descrizione);
    }
}
