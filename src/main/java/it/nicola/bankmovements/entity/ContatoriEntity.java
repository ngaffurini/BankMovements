package it.nicola.bankmovements.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("contatori")
public class ContatoriEntity {

    @Id
    @Indexed(unique = true)
    private String id;
    private Integer sequenceValue;


}
