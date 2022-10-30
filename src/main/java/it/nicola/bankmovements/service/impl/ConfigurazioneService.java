package it.nicola.bankmovements.service.impl;

import it.nicola.bankmovements.common.enums.ErrorType;
import it.nicola.bankmovements.common.exception.CustomNotFoundException;
import it.nicola.bankmovements.dto.ConfigurazioneDto;
import it.nicola.bankmovements.entity.ConfigurazioneEntity;
import it.nicola.bankmovements.mapper.ConfigurazioneMapper;
import it.nicola.bankmovements.repository.ConfigurazioneRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfigurazioneService {

    private final ConfigurazioneMapper configurazioneMapper;
    private final ConfigurazioneRepository configurazioneRepository;

    public ConfigurazioneService(ConfigurazioneMapper configurazioneMapper, ConfigurazioneRepository configurazioneRepository) {
        this.configurazioneMapper = configurazioneMapper;
        this.configurazioneRepository = configurazioneRepository;
    }

    public Page<ConfigurazioneDto> getListConfigurazioneAll(PageRequest pageRequest) {
        Page<ConfigurazioneEntity> configurazioneList = configurazioneRepository.findAll(pageRequest);

        return new PageImpl<>(
                configurazioneMapper.toDtos(configurazioneList.getContent()),
                configurazioneList.getPageable(),
                configurazioneList.getTotalElements());
    }

    public ConfigurazioneDto getConfigurazioneById(String id) {
        Optional<ConfigurazioneEntity> conf = configurazioneRepository.findById(id);
        if(!conf.isPresent())
            throw new CustomNotFoundException(ErrorType.CONFIGURAZIONE_NON_TROVATA);

        return configurazioneMapper.toDto(conf.get());
    }

    public ConfigurazioneDto getConfigurazioneByKey(String key) {
        Optional<ConfigurazioneEntity> conf = configurazioneRepository.findByChiave(key);
        if(!conf.isPresent())
            throw new CustomNotFoundException(ErrorType.CONFIGURAZIONE_NON_TROVATA);

        return configurazioneMapper.toDto(conf.get());
    }

    public void update(ConfigurazioneDto configurazioneDto) {
        configurazioneRepository.save(configurazioneMapper.toEntity(configurazioneDto));
    }

    public void delete(String id) {
        configurazioneRepository.deleteById(id);
    }
}
