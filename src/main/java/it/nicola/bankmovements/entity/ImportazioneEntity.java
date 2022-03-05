package it.nicola.bankmovements.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("importazioni")
public class ImportazioneEntity {

    @Id
    private Integer id;
    private String descrizione;
    private Date dataImportazione;
}
