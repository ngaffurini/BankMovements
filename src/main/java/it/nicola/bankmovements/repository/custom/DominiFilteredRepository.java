package it.nicola.bankmovements.repository.custom;

import it.nicola.bankmovements.entity.DominiEntity;
import it.nicola.bankmovements.model.FiltriDomini;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DominiFilteredRepository {
    Page<DominiEntity> getFilteredList(FiltriDomini filtriDomini, Pageable pageable);
}
