package it.nicola.bankmovements.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document("dominiSpesa")
public class DominiSpesaEntity {

    @Id
    private String id;
    private String descrizione;
    private String categoria;
}
