package it.nicola.bankmovements.excel.resolver;

import it.nicola.bankmovements.entity.DominiEntity;
import it.nicola.bankmovements.entity.MovimentoEntity;
import it.nicola.bankmovements.service.impl.DominiService;
import it.nicola.bankmovements.xls.XLSModel;

public abstract class DynamicAutocompleteMov {

    DominiService dominiService;

    protected DynamicAutocompleteMov(){}

    protected DynamicAutocompleteMov(DominiService dominiService){
        this.dominiService = dominiService;
    }

    public void autocompleteDynamicMovFields(MovimentoEntity mov, XLSModel xlsModel){
        DominiEntity dom = dominiService.getDominioByCodiceAbi(xlsModel.getCausaleABI());

        mov.setCategoria(dom.getCategoria());
        mov.setDescrizione(xlsModel.getDescrizione());
    }
}
