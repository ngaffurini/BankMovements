package it.nicola.bankmovements.repository;

import it.nicola.bankmovements.entity.DominiEntiy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DominiRepository extends MongoRepository<DominiEntiy, String> {

    public DominiEntiy getDominiEntiyByCodiceABI(String codiceABI);

    public List<String> getDistinctByCategoria();
}
