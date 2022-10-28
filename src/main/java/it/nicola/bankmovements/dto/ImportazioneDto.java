package it.nicola.bankmovements.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImportazioneDto {

    private Integer id;
    private String descrizione;
    private Date dataImportazione;
    private String nomeFile;
}
