package cornaton.maxence.technicaltest.iexec.service;

import cornaton.maxence.technicaltest.iexec.model.LocalTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class LocalTaskServiceImplTest {
    @Autowired private LocalTaskService localTaskService;

    /**
     * Simple check to ensure a task can be created.
     */
    @Test
    void createTask() {
        Assertions.assertTrue(localTaskService.createTask(new LocalTask(LocalDateTime.now())));
    }

    /**
     * Simple check to ensure that when we add a new task, it is effectively counted.
     */
    @Test
    void countTasks() {
        Assertions.assertEquals(0, localTaskService.countTasks());
        Assertions.assertTrue(localTaskService.createTask(new LocalTask(LocalDateTime.now())));
        Assertions.assertEquals(1, localTaskService.countTasks());
        for (int i = 0; i < 10; i++) {
            localTaskService.createTask(new LocalTask(LocalDateTime.now()));
        }
        Assertions.assertEquals(11, localTaskService.countTasks());
    }
}
