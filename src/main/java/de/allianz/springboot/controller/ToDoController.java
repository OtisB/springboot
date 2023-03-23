package de.allianz.springboot.controller;

import de.allianz.springboot.dto.ToDoCreateDTO;
import de.allianz.springboot.dto.ToDoUpdateDTO;
import de.allianz.springboot.entity.ToDo;
import de.allianz.springboot.service.ToDoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;

    @PostMapping
    public ToDo create(@Valid @RequestBody ToDoCreateDTO toDoCreateDTO)
    {
        ToDo toDo = new ToDo();
        toDo.setName(toDoCreateDTO.getName());
        toDo.setDate(toDoCreateDTO.getDate());
        return this.toDoService.createToDo(toDo);
    }

    @PutMapping
    public ToDo update(@Valid @RequestBody ToDoUpdateDTO toDoUpdateDTO)
    {
        ToDo toDo = new ToDo();
        toDo.setId(toDoUpdateDTO.getId());
        toDo.setName(toDoUpdateDTO.getName());
        toDo.setDate(toDoUpdateDTO.getDate());
        toDo.setStatus(toDoUpdateDTO.getStatus());
        return this.toDoService.updateToDo(toDo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.toDoService.deleteToDo(id);
    }

    @GetMapping("/{id}")
    public ToDo getById(@PathVariable("id") Long id) {
        return this.toDoService.getToDo(id);
    }

    @GetMapping
    public List<ToDo> getAll() {
        return this.toDoService.getAllToDos();
    }

    @GetMapping("/completed")
    public List<ToDo> getAllByStatusIsTrue() {
        return this.toDoService.getAllCompletedToDos();
    }

    @GetMapping("/uncompleted")
    public List<ToDo> getAllByStatusIsFalse() {
        return this.toDoService.getAllUncompletedToDos();
    }

    @GetMapping("/count-completed")
    public Long countAllCompletedToDos() {
        return this.toDoService.countAllCompletedToDos();
    }

    @GetMapping("/count-uncompleted")
    public Long countAllUncompletedToDos() {
        return this.toDoService.countAllUncompletedToDos();
    }






}
