package de.allianz.springboot.controller;

import de.allianz.springboot.dto.ToDoCreateDTO;
import de.allianz.springboot.dto.ToDoUpdateDTO;
import de.allianz.springboot.entity.ToDo;
import de.allianz.springboot.service.ToDoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;
    private final ModelMapper modelMapper;

    /**
     * Saves a new Todo to DB
     * @param toDoCreateDTO used for validation
     * @return New ToDo for further usage
     */
    @PostMapping
    public ResponseEntity<ToDo> create(@Valid @RequestBody ToDoCreateDTO toDoCreateDTO)
    {
        return new ResponseEntity<>(this.toDoService.createToDo(modelMapper.map(toDoCreateDTO, ToDo.class)), HttpStatus.CREATED);
    }

    /**
     * Updates ToDo wich matches to DTOs id
     * * @param toDoUpdateDTO used for validation
     * @return Updated or new ToDo for further usage
     */
    @PutMapping
    public ResponseEntity<ToDo> update(@Valid @RequestBody ToDoUpdateDTO toDoUpdateDTO)
    {
        ToDo toDo = toDoService.getToDo(toDoUpdateDTO.getId());
        this.modelMapper.map(toDoUpdateDTO, toDo);
        return new ResponseEntity<>(this.toDoService.updateToDo(toDo), HttpStatus.OK);
    }

    /**
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.toDoService.deleteToDo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.toDoService.getToDo(id), HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ToDo>> getAll() {

        return new ResponseEntity<>(this.toDoService.getAllToDos(), HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping("/completed")
    public ResponseEntity<List<ToDo>> getAllByStatusIsTrue() {

        return new ResponseEntity<>(this.toDoService.getAllCompletedToDos(), HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping("/uncompleted")
    public ResponseEntity<List<ToDo>> getAllByStatusIsFalse() {
        return new ResponseEntity<>(this.toDoService.getAllUncompletedToDos(), HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping("/count-completed")
    public ResponseEntity<Long> countAllCompletedToDos() {
        return new ResponseEntity<>(this.toDoService.countAllCompletedToDos(), HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping("/count-uncompleted")
    public ResponseEntity<Long> countAllUncompletedToDos() {

        return new ResponseEntity<>(this.toDoService.countAllUncompletedToDos(), HttpStatus.OK);
    }






}
