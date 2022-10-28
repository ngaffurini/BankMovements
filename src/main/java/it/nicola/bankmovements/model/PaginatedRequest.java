package it.nicola.bankmovements.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginatedRequest<T> {
    protected T request;
    protected JsonPageRequest paginator;
}
