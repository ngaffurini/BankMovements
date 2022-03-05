package it.nicola.bankmovements.excel.resolver;

import it.nicola.bankmovements.entity.DominiEntiy;
import it.nicola.bankmovements.entity.MovimentoEntity;
import it.nicola.bankmovements.service.impl.DominiService;
import it.nicola.bankmovements.xls.XLSModel;

public class ResolverMovimentiStipendi extends DynamicAutocompleteMov{

    public ResolverMovimentiStipendi(){}

    public ResolverMovimentiStipendi(DominiService dominiService){
        super(dominiService, null);
    }

    @Override
    public void autocompleteDynamicMovFields(MovimentoEntity mov, XLSModel xlsModel){
        DominiEntiy dom = dominiService.getDominioByCodiceABI(xlsModel.getCausaleABI());

        mov.setCategoria(dom.getCategoria());
        mov.setDescrizione(dom.getDescrizione());
    }
}
