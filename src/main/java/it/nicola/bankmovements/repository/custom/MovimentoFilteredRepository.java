package it.nicola.bankmovements.repository.custom;

import it.nicola.bankmovements.entity.MovimentoEntity;
import it.nicola.bankmovements.model.FiltriMovimenti;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovimentoFilteredRepository {
    Page<MovimentoEntity> findMovimentoEntityByProperties(FiltriMovimenti movimenti, Pageable pageable);
}
