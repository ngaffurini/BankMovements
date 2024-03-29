package it.nicola.bankmovements.mapper;

import it.nicola.bankmovements.dto.MovimentoDto;
import it.nicola.bankmovements.entity.MovimentoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovimentoMapper extends DtoToEntityBidirectionalMapper<MovimentoDto, MovimentoEntity>{

}
