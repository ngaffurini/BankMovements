package it.nicola.bankmovements.repository;

import it.nicola.bankmovements.entity.DominiEntiy;
import it.nicola.bankmovements.entity.DominiSpesaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface DominiSpesaRepository extends MongoRepository<DominiSpesaEntity, String> {

    DominiSpesaEntity findFirstByDescrizione(String descrizione);

    List<String> getDistinctByCategoria();
}
