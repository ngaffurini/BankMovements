package it.nicola.bankmovements.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovimentoDto {
    private String id;
    private String descrizione;
    private Date data;
    private Double importo;
    private String categoria;
    private Integer nImportazione;
    private Boolean valido;

    private List<String> categorieChoice;
}
