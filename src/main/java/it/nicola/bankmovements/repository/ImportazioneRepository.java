package it.nicola.bankmovements.repository;

import it.nicola.bankmovements.entity.ImportazioneEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportazioneRepository extends MongoRepository<ImportazioneEntity, Integer> {

    public List<ImportazioneEntity> findAll();

    public ImportazioneEntity insert(ImportazioneEntity importazioneEntity);

    public ImportazioneEntity findFirstByDescrizione(String descrizione);
}
