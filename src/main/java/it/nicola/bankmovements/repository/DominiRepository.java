package it.nicola.bankmovements.repository;

import it.nicola.bankmovements.entity.DominiEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DominiRepository extends MongoRepository<DominiEntity, String> {

    DominiEntity getFirstByCodiceAbi(String codiceABI);

    List<String> getDistinctByCategoria();

    DominiEntity findFirstByDescrizione(String descrizione);

    DominiEntity findFirstByDescrizioneAndCodiceAbi(String descrizione, String codiceAbi);
}
