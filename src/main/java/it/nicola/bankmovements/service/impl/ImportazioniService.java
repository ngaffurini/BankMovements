package it.nicola.bankmovements.service.impl;

import it.nicola.bankmovements.entity.ImportazioneEntity;
import it.nicola.bankmovements.repository.ImportazioneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ImportazioniService {

    private final ImportazioneRepository importazioneRepository;

    public ImportazioniService(ImportazioneRepository importazioneRepository) {
        this.importazioneRepository = importazioneRepository;
    }

    public List<ImportazioneEntity> getList() {
        return importazioneRepository.findAll();
    }

    public ImportazioneEntity insertImportazioni(ImportazioneEntity importazione){
        return importazioneRepository.insert(importazione);
    }

    public ImportazioneEntity getImportazioneEntityByDescrizione(String descrizione){
        return importazioneRepository.findFirstByDescrizione(descrizione);
    }
}
