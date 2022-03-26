package it.nicola.bankmovements.excel.resolver;

import it.nicola.bankmovements.entity.DominiEntity;
import it.nicola.bankmovements.entity.MovimentoEntity;
import it.nicola.bankmovements.service.impl.DominiService;
import it.nicola.bankmovements.xls.XLSModel;

public class ResolverMovimentiAddebito extends DynamicAutocompleteMov {

    private static final String CARICAMENTO_PREPAGATA = "CARICAMENTO CARTA NUMERO";
    private static final String RICARICA_PREPAGATA = "RICARICA PREPAGATA";
    private static final String PAGAMENTO_AUTOSTRADA = "PAGAMENTO SERVIZI FASTPAY";
    private static final String PAG_FASTPAY = "FASTPAY N. 1";

    public ResolverMovimentiAddebito(){}

    public ResolverMovimentiAddebito(DominiService dominiService){
        super(dominiService);
    }

    @Override
    public void autocompleteDynamicMovFields(MovimentoEntity mov, XLSModel xlsModel){
        if(xlsModel.getDescrizione().contains("FAV. DI ")) {
            int startIndex = xlsModel.getDescrizione().indexOf("FAV. DI ") + 8;
            int endIndex = startIndex + 29;
            String descrizione = xlsModel.getDescrizione().substring(startIndex, endIndex).trim();

            DominiEntity ds = dominiService.getDominioByDescrizione(descrizione);

            mov.setCategoria(ds.getCategoria());
            mov.setDescrizione(descrizione);
        }else if(xlsModel.getDescrizione().contains(CARICAMENTO_PREPAGATA)){
            DominiEntity dom = dominiService.getDominioByDescrizione(RICARICA_PREPAGATA);

            mov.setCategoria(dom.getCategoria());
            mov.setDescrizione(dom.getDescrizione());
        }else if(xlsModel.getDescrizione().contains(PAGAMENTO_AUTOSTRADA)){
            DominiEntity dom = dominiService.getDominioByDescrizione(PAG_FASTPAY);

            mov.setCategoria(dom.getCategoria());
            mov.setDescrizione(dom.getDescrizione());
        }
    }
}
