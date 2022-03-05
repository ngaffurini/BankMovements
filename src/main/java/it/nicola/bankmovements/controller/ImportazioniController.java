package it.nicola.bankmovements.controller;

import it.nicola.bankmovements.entity.ImportazioneEntity;
import it.nicola.bankmovements.service.impl.ImportazioniService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/importazioni")
public class ImportazioniController {

    private final ImportazioniService importazioniService;

    public ImportazioniController(ImportazioniService importazioniService) {
        this.importazioniService = importazioniService;
    }

    @GetMapping("/list")
    public List<ImportazioneEntity> getList(){
        return importazioniService.getList();
    }

    @GetMapping("/getByDescrizione")
    public ImportazioneEntity getList(@RequestAttribute String descrizione){
        return importazioniService.getImportazioneEntityByDescrizione(descrizione);
    }
}
