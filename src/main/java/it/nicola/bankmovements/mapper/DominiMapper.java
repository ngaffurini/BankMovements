package it.nicola.bankmovements.mapper;

import it.nicola.bankmovements.dto.DominiDto;
import it.nicola.bankmovements.entity.DominiEntity;
import org.mapstruct.Mapper;

@Mapper
public interface DominiMapper {

    DominiDto entityToDto(DominiEntity dominiEntity);

    DominiEntity dtoToEntity(DominiDto dominiDto);
}
