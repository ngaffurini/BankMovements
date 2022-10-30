package it.nicola.bankmovements.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document("configurazione")
public class ConfigurazioneEntity {
    private String id;
    private String chiave;
    private String descrizione;
    private String valore;
}
