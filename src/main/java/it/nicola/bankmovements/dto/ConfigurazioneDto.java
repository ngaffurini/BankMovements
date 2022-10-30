package it.nicola.bankmovements.dto;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConfigurazioneDto {
    private String id;
    private String chiave;
    private String descrizione;
    private String valore;
}

