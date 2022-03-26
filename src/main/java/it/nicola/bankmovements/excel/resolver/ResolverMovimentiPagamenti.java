package it.nicola.bankmovements.excel.resolver;

import it.nicola.bankmovements.entity.DominiEntity;
import it.nicola.bankmovements.entity.MovimentoEntity;
import it.nicola.bankmovements.service.impl.DominiService;
import it.nicola.bankmovements.xls.XLSModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResolverMovimentiPagamenti extends DynamicAutocompleteMov{

    public ResolverMovimentiPagamenti(){}

    public ResolverMovimentiPagamenti(DominiService dominiService){
        super(dominiService);
    }

    @Override
    public void autocompleteDynamicMovFields(MovimentoEntity mov, XLSModel xlsModel){
        String descrizione;
        if(xlsModel.getDescrizione().contains("\\ \\")){
            descrizione = xlsModel.getDescrizione().substring(xlsModel.getDescrizione().indexOf("PRESSO ") + 7, xlsModel.getDescrizione().indexOf("\\ \\")).trim();
        }else if(xlsModel.getDescrizione().contains("ESERCENTE")){
            descrizione = xlsModel.getDescrizione().substring(xlsModel.getDescrizione().indexOf("ESERCENTE ") + 10).trim();
        }else{
            descrizione = xlsModel.getDescrizione();
        }

        DominiEntity ds = dominiService.getDominioByDescrizioneAndCodiceAbi(descrizione, xlsModel.getCausaleABI());

        if(ds != null) {
            mov.setCategoria(ds.getCategoria());
        }

        mov.setDescrizione(descrizione);
    }
}
