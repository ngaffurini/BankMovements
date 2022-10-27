package it.nicola.bankmovements.mapper;

import it.nicola.bankmovements.dto.MovimentoDto;
import it.nicola.bankmovements.entity.MovimentoEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class MovimentiMapperTest {

    private final MovimentoMapper movimentoMapper;

    public MovimentiMapperTest(MovimentoMapper movimentoMapper) {
        this.movimentoMapper = movimentoMapper;
    }

    @Test
    void toDto(){
        MovimentoDto dto = movimentoMapper.toDto(getMovimentoEntity());
        assertTrue(dto.equals(getMovimentoDto()));
    }

    @Test
    void toEntity(){
        MovimentoEntity entity = movimentoMapper.toEntity(getMovimentoDto());
        assertTrue(entity.equals(getMovimentoEntity()));
    }

    private MovimentoEntity getMovimentoEntity(){
        return new MovimentoEntity("5879485dsdf", "descrizione", new Date(), 55.0, "Pasti", 2, true);
    }

    private MovimentoDto getMovimentoDto(){
        return new MovimentoDto("5879485dsdf", "descrizione", new Date(), 55.0, "Pasti", 2, true);
    }
}
