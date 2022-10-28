package it.nicola.bankmovements.model;

import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FiltriImportazione {
    private Date dateFrom;
    private Date dateTo;
    private String descrizione;

}
