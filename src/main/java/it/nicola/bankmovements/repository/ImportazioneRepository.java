package it.nicola.bankmovements.repository;

import it.nicola.bankmovements.entity.ImportazioneEntity;
import it.nicola.bankmovements.repository.custom.ImportazioneFilteredRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportazioneRepository extends MongoRepository<ImportazioneEntity, Integer>, ImportazioneFilteredRepository {

    List<ImportazioneEntity> findAll();

    ImportazioneEntity insert(ImportazioneEntity importazioneEntity);

    ImportazioneEntity findFirstByDescrizione(String descrizione);
}
