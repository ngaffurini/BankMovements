package it.nicola.bankmovements.controller;

import it.nicola.bankmovements.dto.DominiDto;
import it.nicola.bankmovements.model.FiltriDomini;
import it.nicola.bankmovements.model.PaginatedRequest;
import it.nicola.bankmovements.service.impl.DominiService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/list")
    public Page<DominiDto> getFilteredList(@RequestBody PaginatedRequest<FiltriDomini> filtriDomini){
        return dominiService.getFilteredList(filtriDomini.getRequest(), filtriDomini.getPaginator().toPageRequest());
    }
}
