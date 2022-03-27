package it.nicola.bankmovements.excel.resolver;

import it.nicola.bankmovements.dto.DominiDto;
import it.nicola.bankmovements.dto.MovimentoDto;
import it.nicola.bankmovements.service.impl.DominiService;
import it.nicola.bankmovements.xls.XLSModel;

public class ResolverMovimentiStipendi extends DynamicAutocompleteMov{

    public ResolverMovimentiStipendi(){}

    public ResolverMovimentiStipendi(DominiService dominiService){
        super(dominiService);
    }

    @Override
    public void autocompleteDynamicMovFields(MovimentoDto mov, XLSModel xlsModel){
        DominiDto dom = dominiService.getDominioByCodiceAbi(xlsModel.getCausaleABI());

        mov.setCategoria(dom.getCategoria());
        mov.setDescrizione(dom.getDescrizione());
    }
}
