package it.nicola.bankmovements.mapper;

import it.nicola.bankmovements.dto.ImportazioneDto;
import it.nicola.bankmovements.entity.ImportazioneEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImportazioneMapper extends DtoToEntityBidirectionalMapper<ImportazioneDto, ImportazioneEntity> {

}
