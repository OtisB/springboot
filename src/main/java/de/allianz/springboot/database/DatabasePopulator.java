package de.allianz.springboot.database;

import de.allianz.springboot.entity.ToDo;
import de.allianz.springboot.repository.ToDoRepository;
import de.allianz.springboot.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabasePopulator implements CommandLineRunner {

    /*
    CONSTRUCTOR BASED DEPENDENCY INJECTION
    WITHOUT RequiredArgsConstructor ANNOTATION OR FINAL

    public ToDoService(@Autowired ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }
    private ToDoRepository toDoRepository;
    */

    /*
    FIELD BASED DEPENDENCY INJECTION
    WITHOUT RequiredArgsConstructor ANNOTATION OR FINAL
        @Autowired
        private ToDoRepository toDoRepository;
    */

    //LONGBOK DEPENDENCY INJECTION
    // WITH RequiredArgsConstructor ANNOTATION AND FINAL
    private final ToDoRepository toDoRepository;

    @Override
    public void run(String... args) throws Exception {

        final ToDo todo1 = new ToDo(null, "learn Java basics", "01.03.2023", true);
        final ToDo todo2 = new ToDo(null, "learn Spring", "21.03.2023", false);
        final ToDo todo3 = new ToDo(null, "Allianz", "01.04.2023", false);

        toDoRepository.saveAll(List.of(todo1, todo2, todo3));
    }
}
