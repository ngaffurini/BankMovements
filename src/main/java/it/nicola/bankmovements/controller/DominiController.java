package it.nicola.bankmovements.controller;

import it.nicola.bankmovements.dto.DominiDto;
import it.nicola.bankmovements.service.impl.DominiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/domini")
public class DominiController {

    final DominiService dominiService;

    public DominiController(DominiService dominiService){
        this.dominiService = dominiService;
    }

    @GetMapping("/categorie")
    public List<String> getDomainsCategoria(){
        return dominiService.getDistinctCategoriaDomini();
    }

    @PostMapping("/insert")
    public DominiDto insertDominiEntity(DominiDto dominio){
        return dominiService.insertDominiEntity(dominio);
    }
}
