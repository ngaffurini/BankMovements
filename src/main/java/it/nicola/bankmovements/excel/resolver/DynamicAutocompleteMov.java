package it.nicola.bankmovements.excel.resolver;

import it.nicola.bankmovements.entity.DominiEntiy;
import it.nicola.bankmovements.entity.MovimentoEntity;
import it.nicola.bankmovements.service.impl.DominiService;
import it.nicola.bankmovements.service.impl.DominiSpesaService;
import it.nicola.bankmovements.xls.XLSModel;

public abstract class DynamicAutocompleteMov {

    DominiService dominiService;
    DominiSpesaService dominiSpesaService;

    public DynamicAutocompleteMov(){}

    public DynamicAutocompleteMov(DominiService dominiService, DominiSpesaService dominiSpesaService){
        this.dominiService = dominiService;
        this.dominiSpesaService = dominiSpesaService;
    }

    public void autocompleteDynamicMovFields(MovimentoEntity mov, XLSModel xlsModel){
        DominiEntiy dom = dominiService.getDominioByCodiceABI(xlsModel.getCausaleABI());

        mov.setCategoria(dom.getCategoria());
        mov.setDescrizione(xlsModel.getDescrizione());
    }
}
