package it.nicola.bankmovements.repository;

import it.nicola.bankmovements.entity.ContatoriEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoriRepository extends MongoRepository<ContatoriEntity, String> {
}
