package it.nicola.bankmovements.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FiltriMovimenti {
    private String categoria;
    private String descrizione;
    private Date data;
    private Double importo;
}
