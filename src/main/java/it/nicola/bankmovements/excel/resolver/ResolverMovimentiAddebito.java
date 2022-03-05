package it.nicola.bankmovements.excel.resolver;

import it.nicola.bankmovements.entity.DominiEntiy;
import it.nicola.bankmovements.entity.DominiSpesaEntity;
import it.nicola.bankmovements.entity.MovimentoEntity;
import it.nicola.bankmovements.service.impl.DominiService;
import it.nicola.bankmovements.service.impl.DominiSpesaService;
import it.nicola.bankmovements.xls.XLSModel;

public class ResolverMovimentiAddebito extends DynamicAutocompleteMov {

    public ResolverMovimentiAddebito(){}

    public ResolverMovimentiAddebito(DominiService dominiService, DominiSpesaService dominiSpesaService){
        super(dominiService, dominiSpesaService);
    }

    @Override
    public void autocompleteDynamicMovFields(MovimentoEntity mov, XLSModel xlsModel){
        if(xlsModel.getDescrizione().contains("FAV. DI ")) {
            int startIndex = xlsModel.getDescrizione().indexOf("FAV. DI ") + 8;
            int endIndex = startIndex + 29;
            String descrizione = xlsModel.getDescrizione().substring(startIndex, endIndex).trim();

            DominiSpesaEntity ds = dominiSpesaService.getDominioSpesaByDescrizione(descrizione);

            mov.setCategoria(ds.getCategoria());
            mov.setDescrizione(descrizione);
        }else{
            DominiEntiy dom = dominiService.getDominioByCodiceABI(xlsModel.getCausaleABI());

            mov.setCategoria(dom.getCategoria());
            mov.setDescrizione(dom.getDescrizione());
        }
    }
}
