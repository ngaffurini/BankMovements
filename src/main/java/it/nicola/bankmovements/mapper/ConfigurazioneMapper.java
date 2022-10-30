package it.nicola.bankmovements.mapper;

import it.nicola.bankmovements.dto.ConfigurazioneDto;
import it.nicola.bankmovements.entity.ConfigurazioneEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConfigurazioneMapper extends DtoToEntityBidirectionalMapper<ConfigurazioneDto, ConfigurazioneEntity> {
}
