package fr.norsys.hunters;

import fr.norsys.models.Hunter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HunterRepository extends CrudRepository<Hunter, Long> {
    List<Hunter> findAll();
    Hunter findByUsername(@Param("username") String username);

}