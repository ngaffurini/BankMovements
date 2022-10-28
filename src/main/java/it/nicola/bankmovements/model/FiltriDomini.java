package it.nicola.bankmovements.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FiltriDomini {

    private String codiceAbi;
    private String descrizione;
    private String categoria;
}
