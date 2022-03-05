package it.nicola.bankmovements.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JsonPageRequest {
    Integer numeroPagina;
    Integer numeroRighe;
    Ordine ordine;

    public PageRequest toPageRequest(){
        if(ordine == null){
            return PageRequest.of(numeroPagina, numeroRighe);
        }else{
            Sort sort = ordine.isDescending() ? Sort.by(ordine.getOrdine()).descending() : Sort.by(ordine.getOrdine()).ascending();

            return PageRequest.of(numeroPagina, numeroRighe, sort);
        }
    }
}
