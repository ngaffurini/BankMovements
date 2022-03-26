package it.nicola.bankmovements.mapper;

import it.nicola.bankmovements.dto.DominiDto;
import it.nicola.bankmovements.entity.DominiEntity;
import it.nicola.bankmovements.entity.DominiEntity.DominiEntityBuilder;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-26T23:27:07+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
public class DominiMapperImpl implements DominiMapper {

    @Override
    public DominiDto entityToDto(DominiEntity dominiEntity) {
        if ( dominiEntity == null ) {
            return null;
        }

        DominiDto dominiDto = new DominiDto();

        dominiDto.setId( dominiEntity.getId() );
        dominiDto.setCodiceAbi( dominiEntity.getCodiceAbi() );
        dominiDto.setDescrizione( dominiEntity.getDescrizione() );
        dominiDto.setCategoria( dominiEntity.getCategoria() );

        return dominiDto;
    }

    @Override
    public DominiEntity dtoToEntity(DominiDto dominiDto) {
        if ( dominiDto == null ) {
            return null;
        }

        DominiEntityBuilder dominiEntity = DominiEntity.builder();

        dominiEntity.id( dominiDto.getId() );
        dominiEntity.codiceAbi( dominiDto.getCodiceAbi() );
        dominiEntity.descrizione( dominiDto.getDescrizione() );
        dominiEntity.categoria( dominiDto.getCategoria() );

        return dominiEntity.build();
    }
}
