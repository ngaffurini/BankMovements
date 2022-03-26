package it.nicola.bankmovements.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DominiDto {
    private String id;
    private String codiceAbi;
    private String descrizione;
    private String categoria;
}
