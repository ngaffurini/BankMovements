package it.nicola.bankmovements.xls;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class XLSModel {

    private Date data;
    private String causaleABI;
    private String descrizione;
    private Double importo;
}
