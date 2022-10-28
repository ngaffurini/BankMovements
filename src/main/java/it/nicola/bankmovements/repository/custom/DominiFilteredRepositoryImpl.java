package it.nicola.bankmovements.repository.custom;

import it.nicola.bankmovements.entity.DominiEntity;
import it.nicola.bankmovements.entity.ImportazioneEntity;
import it.nicola.bankmovements.model.FiltriDomini;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

public class DominiFilteredRepositoryImpl implements DominiFilteredRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Page<DominiEntity> getFilteredList(FiltriDomini filtriDomini, Pageable pageable) {
        final Query query = new Query();

        final List<Criteria> criteriaList = new ArrayList<>();
        if(StringUtils.isNotBlank(filtriDomini.getCodiceAbi()))
            criteriaList.add(Criteria.where("codiceAbi").is(filtriDomini.getCodiceAbi()));
        if(StringUtils.isNotBlank(filtriDomini.getCategoria()))
            criteriaList.add(new Criteria("categoria").is(filtriDomini.getCategoria()));
        if(StringUtils.isNotBlank(filtriDomini.getDescrizione()))
            criteriaList.add(Criteria.where("descrizione").regex(filtriDomini.getDescrizione()));

        if(!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()])));
        }

        Long numElementi = mongoTemplate.count(query, DominiEntity.class);

        return new PageImpl<>(
                mongoTemplate.find(query.with(pageable), DominiEntity.class),
                pageable,
                numElementi);
    }
}
