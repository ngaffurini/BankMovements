package it.nicola.bankmovements.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("domini")
public class DominiEntity {

    @Id
    private String id;
    private String codiceAbi;
    private String descrizione;
    private String categoria;
}
