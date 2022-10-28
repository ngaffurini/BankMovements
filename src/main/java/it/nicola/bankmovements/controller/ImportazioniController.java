package it.nicola.bankmovements.controller;

import it.nicola.bankmovements.dto.ImportazioneDto;
import it.nicola.bankmovements.entity.ImportazioneEntity;
import it.nicola.bankmovements.model.FiltriImportazione;
import it.nicola.bankmovements.model.JsonPageRequest;
import it.nicola.bankmovements.model.PaginatedRequest;
import it.nicola.bankmovements.service.impl.ImportazioniService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/importazioni")
public class ImportazioniController {

    private final ImportazioniService importazioniService;

    public ImportazioniController(ImportazioniService importazioniService) {
        this.importazioniService = importazioniService;
    }

    @GetMapping("/list/all")
    public List<ImportazioneDto> getList(){
        return importazioniService.getList();
    }

    @PostMapping("/list")
    public Page<ImportazioneDto> getFilteredList(@RequestBody PaginatedRequest<FiltriImportazione> filtriImportazione){
        return importazioniService.getFilteredList(filtriImportazione.getRequest(), filtriImportazione.getPaginator().toPageRequest());
    }

    @GetMapping("/getByDescrizione")
    public ImportazioneDto getList(@RequestAttribute String descrizione){
        return importazioniService.getImportazioneEntityByDescrizione(descrizione);
    }
}
