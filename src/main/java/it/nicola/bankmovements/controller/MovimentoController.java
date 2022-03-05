package it.nicola.bankmovements.controller;

import it.nicola.bankmovements.entity.MovimentoEntity;
import it.nicola.bankmovements.model.FiltriMovimenti;
import it.nicola.bankmovements.model.PaginatedRequest;
import it.nicola.bankmovements.service.impl.MovimentoService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimenti")
public class MovimentoController {

    public final MovimentoService movimentoService;

    public MovimentoController(MovimentoService movimentoService) {
        this.movimentoService = movimentoService;
    }

    @GetMapping("/list")
    public Page<MovimentoEntity> getList(PaginatedRequest<FiltriMovimenti> paginatedRequest){
        return movimentoService.findAll(paginatedRequest.getRequest(), paginatedRequest.getPaginator().toPageRequest());
    }

    @GetMapping("/importMovimenti")
    public List<MovimentoEntity> importMovimentiFromXls() throws Exception {
        return movimentoService.importMovimentiFromXls();
    }

    @GetMapping("/findByNImportazione")
    public List<MovimentoEntity> findByNImportazione(Integer nImportazione) throws Exception {
        return movimentoService.findByNImportazione(nImportazione);
    }

    @GetMapping("/generateCSVFromNImportazione")
    public void generateCSVFromNImportazione(Integer nImportazione) throws Exception {
        movimentoService.generateCSVFromNImportazione(nImportazione);
    }

    @PostMapping("/saveListMovimenti")
    public void saveListMovimenti(List<MovimentoEntity> movimenti) throws Exception {
        movimentoService.saveAllMovimentiEntity(movimenti);
    }

    @DeleteMapping("/deleteByNImportazione/{nImportazione}")
    public Long deleteByNImportazione(@PathVariable Integer nImportazione){
        return movimentoService.deleteByNImportazione(nImportazione);
    }
}
