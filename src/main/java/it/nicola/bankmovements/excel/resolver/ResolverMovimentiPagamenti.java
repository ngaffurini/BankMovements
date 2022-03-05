package it.nicola.bankmovements.excel.resolver;

import it.nicola.bankmovements.entity.DominiSpesaEntity;
import it.nicola.bankmovements.entity.MovimentoEntity;
import it.nicola.bankmovements.service.impl.DominiService;
import it.nicola.bankmovements.service.impl.DominiSpesaService;
import it.nicola.bankmovements.xls.XLSModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResolverMovimentiPagamenti extends DynamicAutocompleteMov{

    public ResolverMovimentiPagamenti(){}

    public ResolverMovimentiPagamenti(DominiService dominiService, DominiSpesaService dominiSpesaService){
        super(dominiService, dominiSpesaService);
    }

    @Override
    public void autocompleteDynamicMovFields(MovimentoEntity mov, XLSModel xlsModel){
        String descrizione = xlsModel.getDescrizione().substring(xlsModel.getDescrizione().indexOf("ESERCENTE ") + 10);
        DominiSpesaEntity ds = dominiSpesaService.getDominioSpesaByDescrizione(descrizione);

        if(ds != null) {
            mov.setCategoria(ds.getCategoria());
            mov.setDescrizione(descrizione);
        }
    }
}
