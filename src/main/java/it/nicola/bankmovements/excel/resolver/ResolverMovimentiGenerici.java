package it.nicola.bankmovements.excel.resolver;

import it.nicola.bankmovements.service.impl.DominiService;

public class ResolverMovimentiGenerici extends DynamicAutocompleteMov{

    public ResolverMovimentiGenerici(){}

    public ResolverMovimentiGenerici(DominiService dominiService){
        super(dominiService, null);
    }
}
