package it.nicola.bankmovements.repository.custom;

import it.nicola.bankmovements.entity.ImportazioneEntity;
import it.nicola.bankmovements.model.FiltriImportazione;
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

public class ImportazioneFilteredRepositoryImpl implements ImportazioneFilteredRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Page<ImportazioneEntity> getFilteredList(FiltriImportazione request, Pageable pageable) {
        final Query query = new Query();

        final List<Criteria> criteriaList = new ArrayList<>();
        if(request.getDateFrom() != null)
            criteriaList.add(Criteria.where("dataImportazione").gte(request.getDateFrom()));
        else if(request.getDateTo() != null)
            criteriaList.add(new Criteria("dataImportazione").lte(request.getDateTo()));
        else if(StringUtils.isNotBlank(request.getDescrizione()))
            criteriaList.add(Criteria.where("descrizione").regex(request.getDescrizione()));

        if(!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()])));
        }

        Long numElementi = mongoTemplate.count(query, ImportazioneEntity.class);

        return new PageImpl<>(
                mongoTemplate.find(query.with(pageable), ImportazioneEntity.class),
                pageable,
                numElementi);
    }
}
