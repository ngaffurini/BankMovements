package it.nicola.bankmovements.mapper;

import it.nicola.bankmovements.dto.MovimentoDto;
import it.nicola.bankmovements.entity.MovimentoEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-26T22:43:33+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class MovimentoMapperImpl implements MovimentoMapper {

    @Override
    public MovimentoDto entityToDto(MovimentoEntity movimentoEntity) {
        if ( movimentoEntity == null ) {
            return null;
        }

        MovimentoDto movimentoDto = new MovimentoDto();

        movimentoDto.setId( movimentoEntity.getId() );
        movimentoDto.setDescrizione( movimentoEntity.getDescrizione() );
        movimentoDto.setData( movimentoEntity.getData() );
        movimentoDto.setImporto( movimentoEntity.getImporto() );
        movimentoDto.setCategoria( movimentoEntity.getCategoria() );
        movimentoDto.setNImportazione( movimentoEntity.getNImportazione() );
        movimentoDto.setValido( movimentoEntity.getValido() );

        return movimentoDto;
    }

    @Override
    public MovimentoEntity dtoToEntity(MovimentoEntity movimentoEntity) {
        if ( movimentoEntity == null ) {
            return null;
        }

        MovimentoEntity movimentoEntity1 = new MovimentoEntity();

        movimentoEntity1.setId( movimentoEntity.getId() );
        movimentoEntity1.setDescrizione( movimentoEntity.getDescrizione() );
        movimentoEntity1.setData( movimentoEntity.getData() );
        movimentoEntity1.setImporto( movimentoEntity.getImporto() );
        movimentoEntity1.setCategoria( movimentoEntity.getCategoria() );
        movimentoEntity1.setNImportazione( movimentoEntity.getNImportazione() );
        movimentoEntity1.setValido( movimentoEntity.getValido() );

        return movimentoEntity1;
    }
}
