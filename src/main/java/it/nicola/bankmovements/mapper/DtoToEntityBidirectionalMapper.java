package it.nicola.bankmovements.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// FIXME check if needs
public interface DtoToEntityBidirectionalMapper<D, E> {
  // Override only if strictly necessary.
  default List<D> toDtos(List<E> entityList) {
    return entityList != null
        ? entityList.stream().map(this::toDto).collect(Collectors.toList())
        : new ArrayList<>();
  }
  /** Maps an entity to a dto. */
  D toDto(E entity);
  // Override only if strictly necessary.
  default List<E> toEntities(List<D> dtoList) {
    return dtoList != null
        ? dtoList.stream().map(this::toEntity).collect(Collectors.toList())
        : new ArrayList<>();
  }
  /** Maps a dto to an entity. */
  E toEntity(D dto);
}
