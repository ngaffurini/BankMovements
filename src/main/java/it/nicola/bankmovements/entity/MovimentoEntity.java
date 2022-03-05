package it.nicola.bankmovements.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("movimenti")
public class MovimentoEntity {

    @Id
    private String id;
    private String descrizione;
    private Date data;
    private Double importo;
    private String categoria;
    private Integer nImportazione;

    @Transient
    private Boolean isValido;
}
