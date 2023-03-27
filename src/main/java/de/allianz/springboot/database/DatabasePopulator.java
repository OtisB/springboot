package de.allianz.springboot.database;
import de.allianz.springboot.entity.ToDo;
import de.allianz.springboot.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabasePopulator implements CommandLineRunner {

    /*
    CONSTRUCTOR BASED DEPENDENCY INJECTION
    WITHOUT RequiredArgsConstructor ANNOTATION OR FINAL

    public ToDoService(@Autowired ToDoService toDoService) {
        this.ToDoService toDoService;
    }
    private ToDoService toDoService;
    */

    /*
    FIELD BASED DEPENDENCY INJECTION
    WITHOUT RequiredArgsConstructor ANNOTATION OR FINAL
        @Autowired
        private ToDoService toDoService;
    */

    //LONGBOK DEPENDENCY INJECTION
    // WITH RequiredArgsConstructor ANNOTATION AND FINAL

    private final ToDoService toDoService;
    @Value("${environmentKey}")
    public String environmentKey;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(environmentKey);
        final ToDo todo1 = new ToDo(null, "learn Java basics", "01.03.2023", true);
        final ToDo todo2 = new ToDo(null, "learn Spring", "21.03.2023", false);
        final ToDo todo3 = new ToDo(null, "Allianz", "01.04.2023", false);

        this.toDoService.createToDo(todo1);
        this.toDoService.createToDo(todo2);
        this.toDoService.createToDo(todo3);

    }
}
