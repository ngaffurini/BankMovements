package it.nicola.bankmovements.common.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ErrorType {
    MOVIMENTO_NON_TROVATO("Attenzione: Movimento non trovato."),
    CONFIGURAZIONE_NON_TROVATA("Attenzione: Configurazione non trovata.");

    @Setter private String descrizione;

    ErrorType(String descrizione) {
        this.descrizione = descrizione;
    }
}
