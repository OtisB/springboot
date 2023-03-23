package de.allianz.springboot.controller;

import de.allianz.springboot.dto.ToDoCreateDTO;
import de.allianz.springboot.dto.ToDoUpdateDTO;
import de.allianz.springboot.entity.ToDo;
import de.allianz.springboot.service.ToDoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ToDo create(@Valid @RequestBody ToDoCreateDTO toDoCreateDTO)
    {
        return this.toDoService.createToDo(modelMapper.map(toDoCreateDTO, ToDo.class));
    }

    /**
     * Updates ToDo wich matches to DTOs id
     * * @param toDoUpdateDTO used for validation
     * @return Updated or new ToDo for further usage
     */
    @PutMapping
    public ToDo update(@Valid @RequestBody ToDoUpdateDTO toDoUpdateDTO)
    {
        ToDo toDo = toDoService.getToDo(toDoUpdateDTO.getId());
        this.modelMapper.map(toDoUpdateDTO, toDo);
        return this.toDoService.updateToDo(toDo);
    }

    /**
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.toDoService.deleteToDo(id);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ToDo getById(@PathVariable("id") Long id) {
        return this.toDoService.getToDo(id);
    }

    /**
     *
     * @return
     */
    @GetMapping
    public List<ToDo> getAll() {
        return this.toDoService.getAllToDos();
    }

    /**
     *
     * @return
     */
    @GetMapping("/completed")
    public List<ToDo> getAllByStatusIsTrue() {
        return this.toDoService.getAllCompletedToDos();
    }

    /**
     *
     * @return
     */
    @GetMapping("/uncompleted")
    public List<ToDo> getAllByStatusIsFalse() {
        return this.toDoService.getAllUncompletedToDos();
    }

    /**
     *
     * @return
     */
    @GetMapping("/count-completed")
    public Long countAllCompletedToDos() {
        return this.toDoService.countAllCompletedToDos();
    }

    /**
     *
     * @return
     */
    @GetMapping("/count-uncompleted")
    public Long countAllUncompletedToDos() {
        return this.toDoService.countAllUncompletedToDos();
    }






}
