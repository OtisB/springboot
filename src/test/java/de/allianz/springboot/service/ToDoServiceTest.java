package de.allianz.springboot.service;

import de.allianz.springboot.repository.ToDoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ToDoServiceTest {

    //Simulates everything in TodoRepo 100% works
    @Mock
    private ToDoRepository toDoRepository;

    @InjectMocks
    private ToDoService toDoService;


    @Test
    void GetToDo() {
       when(this.toDoRepository.findById(any(Long.class))).thenReturn(Optional.empty());

       assertThrows(EntityNotFoundException.class, () -> toDoService.getToDo(1L));
    }

    //ToDo
    //UpdateToDo
}