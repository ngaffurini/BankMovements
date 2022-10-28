package it.nicola.bankmovements.repository;

import it.nicola.bankmovements.entity.DominiEntity;
import it.nicola.bankmovements.repository.custom.DominiFilteredRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DominiRepository extends MongoRepository<DominiEntity, String>, DominiFilteredRepository {

    DominiEntity getFirstByCodiceAbi(String codiceABI);

    List<String> getDistinctByCategoria();

    DominiEntity findFirstByDescrizione(String descrizione);

    DominiEntity findFirstByDescrizioneAndCodiceAbi(String descrizione, String codiceAbi);

    List<DominiEntity> findByDescrizioneAndCodiceAbi(String descrizione, String codiceAbi);

    List<DominiEntity> findByDescrizione(String descrizione);
}
