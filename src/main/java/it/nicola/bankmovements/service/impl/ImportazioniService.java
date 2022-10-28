package it.nicola.bankmovements.service.impl;

import it.nicola.bankmovements.dto.ImportazioneDto;
import it.nicola.bankmovements.entity.ImportazioneEntity;
import it.nicola.bankmovements.mapper.ImportazioneMapper;
import it.nicola.bankmovements.model.FiltriImportazione;
import it.nicola.bankmovements.repository.ImportazioneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ImportazioniService {

    private final ImportazioneRepository importazioneRepository;
    private final ImportazioneMapper importazioneMapper;

    public ImportazioniService(ImportazioneRepository importazioneRepository, ImportazioneMapper importazioneMapper) {
        this.importazioneRepository = importazioneRepository;
        this.importazioneMapper = importazioneMapper;
    }

    public List<ImportazioneDto> getList() {
        return importazioneMapper.toDtos(importazioneRepository.findAll());
    }

    public ImportazioneDto insertImportazioni(ImportazioneEntity importazione){
        return importazioneMapper.toDto(importazioneRepository.insert(importazione));
    }

    public ImportazioneDto getImportazioneEntityByDescrizione(String descrizione){
        return importazioneMapper.toDto(importazioneRepository.findFirstByDescrizione(descrizione));
    }

    public Page<ImportazioneDto> getFilteredList(FiltriImportazione request, PageRequest pageRequest) {
        Page<ImportazioneEntity> importazioni = importazioneRepository.getFilteredList(request, pageRequest);

        return new PageImpl<>(
                importazioneMapper.toDtos(importazioni.getContent()),
                importazioni.getPageable(),
                importazioni.getTotalElements());
    }
}
