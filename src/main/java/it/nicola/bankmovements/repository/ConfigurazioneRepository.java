package it.nicola.bankmovements.repository;

import it.nicola.bankmovements.entity.ConfigurazioneEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfigurazioneRepository extends MongoRepository<ConfigurazioneEntity, String> {

    Optional<ConfigurazioneEntity> findById(String id);

    Optional<ConfigurazioneEntity> findByChiave(String chiave);

    Page<ConfigurazioneEntity> findAll(Pageable pageable);
 }
