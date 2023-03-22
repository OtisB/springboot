package de.allianz.springboot.service;

import de.allianz.springboot.entity.ToDo;
import de.allianz.springboot.repository.ToDoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//holds the business logic/most of the
//only service (and maybe the populator at first) has a connection to the repository
@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;

    /**
     * Save new ToDo to database
     * @param toDo to create
     */
    public void createToDo(ToDo toDo) {
        toDoRepository.save(toDo);
    }

    /**
     * Checks Updates all fields of existing ToDo
     * If no ToDo with given id is found, a new ToDo will be created
     * Throws exception if providet a null object
     * @param toDo to be updated
     */
    public  void updateToDo(ToDo toDo) {
        ToDo updatedToDo = toDoRepository.findById(toDo.getId()).orElseThrow(
                () -> new EntityNotFoundException("TODO NOT FOUND!"));
            updatedToDo.setName(toDo.getName());
            updatedToDo.setDate(toDo.getDate());
            updatedToDo.setStatus(toDo.getStatus());
            this.toDoRepository.save( updatedToDo);
    }

    /**
     * Deletes ToDo with provided id
     * @param id used for delete
     */
    public void deleteToDo(Long id) {
        toDoRepository.deleteById(id);
    }

    /**
     * Find and returns ToDo with provide id
     * @param id used for search
     */
    public ToDo getToDo(Long id) {
        return toDoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("TODO WITH PROVIDED ID NOT FOUND!")
        );
    }

    /**
     * Returns a list with all ToDos in DB
     * @return List with ToDos
     */
    public List<ToDo> getAllToDos() {
       return (List<ToDo>) this.toDoRepository.findAll();
    }

    /**
     * Returns a list with all ToDos in DB where status is true
     * @ List with finished ToDoes
     */
    public List<ToDo> getAllCompletedToDos() {
        return this.toDoRepository.findAllByStatus(true);
    }

    /**
     * Returns a list with all ToDos in DB where status is false
     * @return List with unfinished ToDoes
     */
    public List<ToDo> getAllUncompletedToDos() {
        return this.toDoRepository.findAllByStatus(false);
    }

    /**
     * Counts all ToDos where status is true
     * @return Long value representing finished ToDos
     */
    public Long countAllCompletedToDos() {
        return this.toDoRepository.countAllByStatus(true);
    }

    /**
     * Counts all ToDos where status is false
     * @return Long value representing unfinished ToDos
     */
    public Long countAllUncompletedToDos() {
        return this.toDoRepository.countAllByStatus(false);
    }


}
