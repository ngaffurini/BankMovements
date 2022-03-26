package it.nicola.bankmovements.service.impl;

import it.nicola.bankmovements.entity.DominiEntity;
import it.nicola.bankmovements.repository.DominiRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DominiService {

    private DominiRepository dominiRepository;

    public DominiService(DominiRepository dominiRepository) {
        this.dominiRepository = dominiRepository;
    }

    public DominiEntity getDominioByCodiceAbi(String codiceAbi){
        return dominiRepository.getDominiEntityByCodiceAbi(codiceAbi);
    }

    public List<String> getDistinctCategoriaDomini(){
        return dominiRepository.getDistinctByCategoria();
    }

    public DominiEntity getDominioByDescrizione(String descrizione) {
        return dominiRepository.findFirstByDescrizione(descrizione);
    }

    public DominiEntity getDominioByDescrizioneAndCodiceAbi(String descrizione, String codiceAbi) {
        return dominiRepository.findFirstByDescrizioneAndCodiceAbi(descrizione, codiceAbi);
    }

    public DominiEntity insertDominiEntity(DominiEntity dominio){
        return dominiRepository.save(dominio);
    }
}
