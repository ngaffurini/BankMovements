package it.nicola.bankmovements.service.impl;

import it.nicola.bankmovements.entity.DominiSpesaEntity;
import it.nicola.bankmovements.repository.DominiSpesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DominiSpesaService {
    private final DominiSpesaRepository dominiSpesaRepository;

    public DominiSpesaService(DominiSpesaRepository dominiSpesaRepository) {
        this.dominiSpesaRepository = dominiSpesaRepository;
    }

    public DominiSpesaEntity getDominioSpesaByDescrizione(String descrizione){
        return dominiSpesaRepository.findFirstByDescrizione(descrizione);
    }

    public List<String> getDistinctCategoriaDominiSpesa(){
        return dominiSpesaRepository.getDistinctByCategoria();
    }
}
