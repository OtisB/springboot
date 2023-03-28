package de.allianz.springboot.controller;

import de.allianz.springboot.entity.ToDo;
import de.allianz.springboot.service.ToDoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDoService toDoService;

    //Although ModelMapper isn't used here, Controllers constructor use it, so it is needed here
    @MockBean
    private ModelMapper modelMapper;

    private ToDo todo1;
    private ToDo todo2;
    private ToDo todo3;
    private List<ToDo> todos;

    @BeforeEach
    public void init() {
        todo1 = new ToDo(1L, "learn Java basics", "01.03.2023", true);
        todo2 = new ToDo(2L, "learn Spring", "21.03.2023", false);
        todo3 = new ToDo(3L, "Allianz", "01.04.2023", false);

        this.todos = Arrays.asList(todo1, todo2,todo3);
    }

    @Test
    void shouldGetAllToDos() throws Exception {

        //Simulates service would provide us with todos when called
        when(this.toDoService.getAllToDos()).thenReturn(this.todos);

        this.mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                    """
                        [
                            {
                                "name": "learn Java basics",
                                "date": "01.03.2023",
                                "status": true,
                                "id": 1
                            },
                            {
                                "name": "learn Spring",
                                "date": "21.03.2023",
                                "status": false,
                                "id": 2
                            },
                            {
                                "name": "Allianz",
                                "date": "01.04.2023",
                                "status": false,
                                "id": 3
                            }
                        ]
                    
                    """
                ));
    }

    @Test
    void shouldCreateToDo() throws Exception{

        ToDo toDo4 = new ToDo(4L, "Workout", "29.02.2024", false);

        when(this.toDoService.createToDo(any())).thenReturn(toDo4);

        this.mockMvc.perform(
                post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                        {
                                                "name": "Workout",
                                                "date": "29.02.2024",
                                                "status": false,
                                                "id": null
                                        }
                                        """
                        ))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldFindToDoById() throws Exception {

        when(this.toDoService.getToDo(any(Long.class))).thenReturn(todo1);

        this.mockMvc.perform(get("/todos/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                {
                                                 "name": "learn Java basics",
                                                 "date": "01.03.2023",
                                                 "status": true,
                                                 "id": 1
                                             }
                                """
                ));
    }
}