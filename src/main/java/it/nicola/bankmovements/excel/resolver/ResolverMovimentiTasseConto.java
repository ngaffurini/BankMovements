package it.nicola.bankmovements.excel.resolver;

import it.nicola.bankmovements.dto.DominiDto;
import it.nicola.bankmovements.dto.MovimentoDto;
import it.nicola.bankmovements.entity.DominiEntity;
import it.nicola.bankmovements.service.impl.DominiService;
import it.nicola.bankmovements.xls.XLSModel;

public class ResolverMovimentiTasseConto extends DynamicAutocompleteMov{

    private static final String IMPOSTA_BOLLO = "IMPOSTA BOLLO";

    public ResolverMovimentiTasseConto(){}

    public ResolverMovimentiTasseConto(DominiService dominiService){
        super(dominiService);
    }

    @Override
    public void autocompleteDynamicMovFields(MovimentoDto mov, XLSModel xlsModel){
        String descrizione = xlsModel.getDescrizione();
        boolean isImpostaBollo = descrizione.startsWith(IMPOSTA_BOLLO);

        DominiDto ds = dominiService.getDominioByDescrizione(isImpostaBollo ? IMPOSTA_BOLLO : descrizione);

        if(ds != null) {
            mov.setCategoria(ds.getCategoria());
            mov.setDescrizione(descrizione);
        }
    }
}
