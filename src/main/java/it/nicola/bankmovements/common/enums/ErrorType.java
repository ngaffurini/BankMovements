package it.nicola.bankmovements.common.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ErrorType {
    MOVIMENTO_NON_TROVATO("Attenzione: Movimento non trovato.");

    @Setter private String descrizione;

    ErrorType(String descrizione) {
        this.descrizione = descrizione;
    }
}
