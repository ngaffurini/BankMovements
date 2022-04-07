package it.nicola.bankmovements.repository;

import it.nicola.bankmovements.entity.MovimentoEntity;
import it.nicola.bankmovements.model.FiltriMovimenti;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovimentoFilteredRepositoryImpl implements MovimentoFilteredRepository{

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Page<MovimentoEntity> findMovimentoEntityByProperties(FiltriMovimenti movimenti, Pageable pageable) {
        final Query query = new Query();

        final List<Criteria> criteriaList = new ArrayList<>();
        if(StringUtils.isNotBlank(movimenti.getCategoria()))
            criteriaList.add(Criteria.where("categoria").is(movimenti.getCategoria()));
        else if(StringUtils.isNotBlank(movimenti.getDescrizione()))
            criteriaList.add(Criteria.where("descrizione").regex(movimenti.getDescrizione()));
        else if(movimenti.getData() != null)
            criteriaList.add(new Criteria("data").is(movimenti.getData()));
        else if(movimenti.getImporto() != null)
            criteriaList.add(new Criteria("importo").is(movimenti.getImporto()));
        else if(movimenti.getNImportazione() != null)
            criteriaList.add(new Criteria("nImportazione").is(movimenti.getNImportazione()));


        if(!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()])));
        }

        Long numElementi = mongoTemplate.count(query, MovimentoEntity.class);

        query.with(pageable);

        return new PageImpl<>(
                mongoTemplate.find(query, MovimentoEntity.class),
                pageable,
                numElementi);


       // return null;
    }
}
