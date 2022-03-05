package it.nicola.bankmovements.repository;

import it.nicola.bankmovements.entity.MovimentoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentoRepository extends MongoRepository<MovimentoEntity, String> {

    List<MovimentoEntity> findAll();

    Page<MovimentoEntity> findAll(Pageable pageable);

    Long deleteBynImportazione(Integer nImportazione);

    List<MovimentoEntity> findBynImportazione(Integer nImportazione);
}
