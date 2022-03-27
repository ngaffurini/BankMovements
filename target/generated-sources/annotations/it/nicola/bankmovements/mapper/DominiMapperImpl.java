package it.nicola.bankmovements.mapper;

import it.nicola.bankmovements.dto.DominiDto;
import it.nicola.bankmovements.entity.DominiEntity;
import it.nicola.bankmovements.entity.DominiEntity.DominiEntityBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-27T17:48:43+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class DominiMapperImpl implements DominiMapper {

    @Override
    public DominiDto toDto(DominiEntity entity) {
        if ( entity == null ) {
            return null;
        }

        DominiDto dominiDto = new DominiDto();

        dominiDto.setId( entity.getId() );
        dominiDto.setCodiceAbi( entity.getCodiceAbi() );
        dominiDto.setDescrizione( entity.getDescrizione() );
        dominiDto.setCategoria( entity.getCategoria() );

        return dominiDto;
    }

    @Override
    public DominiEntity toEntity(DominiDto dto) {
        if ( dto == null ) {
            return null;
        }

        DominiEntityBuilder dominiEntity = DominiEntity.builder();

        dominiEntity.id( dto.getId() );
        dominiEntity.codiceAbi( dto.getCodiceAbi() );
        dominiEntity.descrizione( dto.getDescrizione() );
        dominiEntity.categoria( dto.getCategoria() );

        return dominiEntity.build();
    }
}
