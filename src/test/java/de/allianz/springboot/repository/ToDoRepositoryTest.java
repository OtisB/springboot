package de.allianz.springboot.repository;
import de.allianz.springboot.entity.ToDo;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//https://github.com/spring-projects/spring-framework/issues/22286
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ToDoRepositoryTest {

    private final ToDoRepository toDoRepository;

    private ToDo todo1;
    private ToDo todo2;
    private ToDo todo3;

    @BeforeEach
    public void init() {
        todo1 = new ToDo(null, "learn Java basics", "01.03.2023", true);
        todo2 = new ToDo(null, "learn Spring", "21.03.2023", false);
        todo3 = new ToDo(null, "Allianz", "01.04.2023", false);

        this.toDoRepository.saveAll(List.of(todo1, todo2, todo3));
    }

    @Test
    public void findAllToDosWhereStatusIsTrue() {
        assertTrue(this.toDoRepository.findAllByStatus(true).contains(todo1));
        assertFalse(this.toDoRepository.findAllByStatus(true).contains(todo2));
        assertFalse(this.toDoRepository.findAllByStatus(true).contains(todo3));
    }
    @Test
    public void findAllToDosWhereStatusIsFalse() {
        assertFalse(this.toDoRepository.findAllByStatus(false).contains(todo1));
        assertTrue(this.toDoRepository.findAllByStatus(false).contains(todo2));
        assertTrue(this.toDoRepository.findAllByStatus(false).contains(todo3));
    }

    @Test
    public void CountAllByStatusIsTrue() {
        assertEquals(1, this.toDoRepository.countAllByStatus(true));
    }

    @Test
    public void CountAllByStatusIsFalse() {
        assertEquals(2, this.toDoRepository.countAllByStatus(false));
    }
}
