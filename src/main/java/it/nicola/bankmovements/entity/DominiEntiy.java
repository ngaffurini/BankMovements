package it.nicola.bankmovements.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("domini")
public class DominiEntiy {

    @Id
    private String id;
    private String codiceABI;
    private String descrizione;
    private String categoria;
    private String nomeMetodo;
}
