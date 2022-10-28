package it.nicola.bankmovements.repository.custom;

import it.nicola.bankmovements.entity.ImportazioneEntity;
import it.nicola.bankmovements.model.FiltriImportazione;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface ImportazioneFilteredRepository {
    Page<ImportazioneEntity> getFilteredList(FiltriImportazione request, Pageable pageable);
}
