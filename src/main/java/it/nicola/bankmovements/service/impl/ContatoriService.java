package it.nicola.bankmovements.service.impl;

import it.nicola.bankmovements.entity.ContatoriEntity;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class ContatoriService {

    private final MongoOperations mongoOperations;

    public ContatoriService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Integer next(String seqName){
        ContatoriEntity counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("sequence_value",1), options().returnNew(true).upsert(true),
                ContatoriEntity.class);
        return !Objects.isNull(counter) ? counter.getSequence_value() : 1;

    }
}
