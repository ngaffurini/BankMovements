package it.nicola.bankmovements.utils;

import it.nicola.bankmovements.entity.DominiEntiy;
import it.nicola.bankmovements.entity.DominiSpesaEntity;
import it.nicola.bankmovements.entity.MovimentoEntity;
import it.nicola.bankmovements.service.impl.DominiService;
import it.nicola.bankmovements.service.impl.DominiSpesaService;
import it.nicola.bankmovements.xls.XLSModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Slf4j
@Component
public class ExcelUtils {

    DominiService dominiService;
    DominiSpesaService dominiSpesaService;

    public ExcelUtils(){

    }

    public ExcelUtils(DominiService dominiService, DominiSpesaService dominiSpesaService){
        this.dominiService = dominiService;
        this.dominiSpesaService = dominiSpesaService;
    }

    public void autocompleteMovimentoEntityFromXls(MovimentoEntity mov, XLSModel row) throws Exception {
        DominiEntiy dom = dominiService.getDominioByCodiceABI(row.getCausaleABI());

        //ExcelUtils.class.getMethod(dom.getNomeMetodo(), MovimentoEntity.class, XLSModel.class, DominiEntiy.class).invoke(new ExcelUtils(), mov, row, dom);
        switch (row.getCausaleABI()){
            case "18":
            case "66":
                setDynamicMovFieldsGenerics(mov, row, dom);
                break;
            case "27":
                setDynamicMovFieldsForStipendi(mov, row, dom);
                break;
            case "43":
                setDynamicMovFieldsForPayments(mov, row, dom);
                break;
            case "50":
                setDynamicMovFieldsForPrepagata(mov, row, dom);
                break;
        }
    }

    public void setDynamicMovFieldsForStipendi(MovimentoEntity mov, XLSModel xlsModel, DominiEntiy dom){
        mov.setCategoria(dom.getCategoria());
        mov.setDescrizione(dom.getDescrizione());

    }

    public void setDynamicMovFieldsForPrepagata(MovimentoEntity mov, XLSModel xlsModel, DominiEntiy dom){
        mov.setCategoria(dom.getCategoria());
        mov.setDescrizione(dom.getDescrizione());
    }

    public void setDynamicMovFieldsForPayments(MovimentoEntity mov, XLSModel xlsModel, DominiEntiy dom){
        String descrizione = xlsModel.getDescrizione().substring(xlsModel.getDescrizione().indexOf("ESERCENTE ") + 10);
        DominiSpesaEntity ds = dominiSpesaService.getDominioSpesaByDescrizione(descrizione);

        if(ds != null) {
            mov.setCategoria(ds.getCategoria());
            mov.setDescrizione(descrizione);
        }

    }

    public void setDynamicMovFieldsGenerics(MovimentoEntity mov, XLSModel xlsModel, DominiEntiy dom){
        mov.setCategoria(dom.getCategoria());
        mov.setDescrizione(xlsModel.getDescrizione());
    }

}
