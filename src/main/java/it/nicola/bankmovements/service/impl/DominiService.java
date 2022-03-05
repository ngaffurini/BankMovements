package it.nicola.bankmovements.service.impl;

import it.nicola.bankmovements.entity.DominiEntiy;
import it.nicola.bankmovements.repository.DominiRepository;
import it.nicola.bankmovements.service.ServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DominiService implements ServiceInterface {

    private DominiRepository dominiRepository;

    public DominiService(DominiRepository dominiRepository) {
        this.dominiRepository = dominiRepository;
    }

    public DominiEntiy getDominioByCodiceABI(String codiceAbi){
        return dominiRepository.getDominiEntiyByCodiceABI(codiceAbi);
    }

    public List<String> getDistinctCategoriaDomini(List<String> listCategoriaDominiSpesa){
        List<String> domini = dominiRepository.getDistinctByCategoria();
        domini.addAll(listCategoriaDominiSpesa);
        return domini;
    }
}
