package it.nicola.bankmovements.controller;

import it.nicola.bankmovements.model.ChiaveValore;
import it.nicola.bankmovements.service.impl.DominiService;
import it.nicola.bankmovements.service.impl.DominiSpesaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/domini")
public class DominiController {

    final DominiService dominiService;
    final DominiSpesaService dominiSpesaService;

    public DominiController(DominiService dominiService, DominiSpesaService dominiSpesaService){
        this.dominiService = dominiService;
        this.dominiSpesaService = dominiSpesaService;
    }

    @GetMapping("/categorie")
    public List<String> getDomainsCategoria(){
        return dominiService.getDistinctCategoriaDomini(dominiSpesaService.getDistinctCategoriaDominiSpesa());
    }
}
