package it.nicola.bankmovements.controller;

import it.nicola.bankmovements.dto.MovimentoDto;
import it.nicola.bankmovements.model.FiltriMovimenti;
import it.nicola.bankmovements.model.JsonPageRequest;
import it.nicola.bankmovements.model.PaginatedRequest;
import it.nicola.bankmovements.service.impl.MovimentoService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/movimenti")
public class MovimentoController {

    public final MovimentoService movimentoService;

    public MovimentoController(MovimentoService movimentoService) {
        this.movimentoService = movimentoService;
    }

    @GetMapping("/list/all")
    public Page<MovimentoDto> findAll(JsonPageRequest pageRequest){
        return movimentoService.findAll(pageRequest.toPageRequest());
    }

    @GetMapping("/list")
    public Page<MovimentoDto> getList(PaginatedRequest<FiltriMovimenti> paginatedRequest){
        return movimentoService.findAll(paginatedRequest.getRequest(), paginatedRequest.getPaginator().toPageRequest());
    }

    @GetMapping("/importMovimenti")
    public Boolean importMovimentiFromXls() throws IOException, ParseException {
        return movimentoService.importMovimentiFromXls();
    }

    @GetMapping("/findByNImportazione")
    public Page<MovimentoDto> findByNImportazione(Integer nImportazione, JsonPageRequest pagination) {
        return movimentoService.findByNImportazione(nImportazione, pagination.toPageRequest());
    }

    @GetMapping("/findByValido")
    public Page<MovimentoDto> findByValido(Boolean valido, JsonPageRequest pagination) {
        return movimentoService.findByValido(valido, pagination.toPageRequest());
    }

    @GetMapping("/generateCSVFromNImportazione")
    public void generateCSVFromNImportazione(Integer nImportazione) throws IOException {
        movimentoService.generateCSVFromNImportazione(nImportazione);
    }

    @PostMapping("/saveListMovimenti")
    public void saveListMovimenti(List<MovimentoDto> movimenti) {
        movimentoService.saveAllMovimentiEntity(movimenti);
    }

    @DeleteMapping("/deleteByNImportazione/{nImportazione}")
    public Long deleteByNImportazione(@PathVariable Integer nImportazione){
        return movimentoService.deleteByNImportazione(nImportazione);
    }

    @GetMapping("/findByValidoAndNImportazione")
    public Page<MovimentoDto> findByValidoAndNImportazione(Boolean valido, Integer nImportazione, JsonPageRequest pageRequest){
        return movimentoService.findByValidoAndNImportazione(valido, nImportazione, pageRequest.toPageRequest());
    }
}
