package it.nicola.bankmovements.repository;

import it.nicola.bankmovements.entity.MovimentoEntity;
import it.nicola.bankmovements.repository.custom.MovimentoFilteredRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovimentoRepository extends MongoRepository<MovimentoEntity, String>, MovimentoFilteredRepository {

    Optional<MovimentoEntity> findById(String id);

    List<MovimentoEntity> findAll();

    Page<MovimentoEntity> findAll(Pageable pageable);

    Long deleteBynImportazione(Integer nImportazione);

    List<MovimentoEntity> findBynImportazione(Integer nImportazione);

    Page<MovimentoEntity> findBynImportazioneOrderByValido(Integer nImportazione, Pageable pageable);

    Page<MovimentoEntity> findByValido(Boolean valido, Pageable pageable);

    Page<MovimentoEntity> findByValidoAndNImportazione(Boolean valido, Integer nImportazione, Pageable pageable);
}
