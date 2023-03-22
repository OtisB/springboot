package de.allianz.springboot.repository;

import de.allianz.springboot.entity.ToDo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends CrudRepository<ToDo, Long> {

    //Only use with care, when must and when sure DB will not change anymore
    @Query(value = "SELECT * FROM ToDo WHERE status = true", nativeQuery = true)
    List<ToDo> manualFindAllByStatusIsTrue();

    @Query(value = "SELECT COUNT(*) FROM ToDo WHERE status = true", nativeQuery = true)
    Long manualCountAllByStatusIsTrue();

    //derived methods
    //SpringBoot generates SQL queries for you
     List<ToDo> findAllByStatusIsTrue();
     List<ToDo> findAllByStatusIsFalse();
     Long countAllByStatusIsTrue();
     Long countAllByStatusIsFalse();


    //generic
    List<ToDo> findAllByStatus(Boolean status);

    Long countAllByStatus(Boolean status);
}
