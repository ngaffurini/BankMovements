package it.nicola.bankmovements.mapper;

import it.nicola.bankmovements.dto.MovimentoDto;
import it.nicola.bankmovements.entity.MovimentoEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-26T23:22:08+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class MovimentoMapperImpl implements MovimentoMapper {

    @Override
    public MovimentoDto toDto(MovimentoEntity entity) {
        if ( entity == null ) {
            return null;
        }

        MovimentoDto movimentoDto = new MovimentoDto();

        movimentoDto.setId( entity.getId() );
        movimentoDto.setDescrizione( entity.getDescrizione() );
        movimentoDto.setData( entity.getData() );
        movimentoDto.setImporto( entity.getImporto() );
        movimentoDto.setCategoria( entity.getCategoria() );
        movimentoDto.setNImportazione( entity.getNImportazione() );
        movimentoDto.setValido( entity.getValido() );

        return movimentoDto;
    }

    @Override
    public MovimentoEntity toEntity(MovimentoDto dto) {
        if ( dto == null ) {
            return null;
        }

        MovimentoEntity movimentoEntity = new MovimentoEntity();

        movimentoEntity.setId( dto.getId() );
        movimentoEntity.setDescrizione( dto.getDescrizione() );
        movimentoEntity.setData( dto.getData() );
        movimentoEntity.setImporto( dto.getImporto() );
        movimentoEntity.setCategoria( dto.getCategoria() );
        movimentoEntity.setNImportazione( dto.getNImportazione() );
        movimentoEntity.setValido( dto.getValido() );

        return movimentoEntity;
    }
}
