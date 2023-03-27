package de.allianz.springboot.controller;

import de.allianz.springboot.entity.ToDo;
import de.allianz.springboot.service.ToDoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDoService toDoService;

    private List<ToDo> todos;

    @BeforeEach
    public void init() {
        this.todos = Arrays.asList(
        new ToDo(1L, "learn Java basics", "01.03.2023", true),
        new ToDo(2L, "learn Spring", "21.03.2023", false),
        new ToDo(3L, "Allianz", "01.04.2023", false)
        );
    }

    @Test
    void shouldGetAllToDos() throws Exception {
        when(toDoService.getAllToDos()).thenReturn(this.todos);

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/todos"))
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
}