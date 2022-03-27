package it.nicola.bankmovements.mapper;

import it.nicola.bankmovements.dto.DominiDto;
import it.nicola.bankmovements.entity.DominiEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DominiMapper extends DtoToEntityBidirectionalMapper<DominiDto, DominiEntity> {

}
