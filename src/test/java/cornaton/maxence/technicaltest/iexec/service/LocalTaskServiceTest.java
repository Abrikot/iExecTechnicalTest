package cornaton.maxence.technicaltest.iexec.service;

import cornaton.maxence.technicaltest.iexec.exceptions.DatabaseException;
import cornaton.maxence.technicaltest.iexec.model.LocalTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

/**
 * Define a set of common tests for all types of {@link LocalTaskService}s.
 */
public abstract class LocalTaskServiceTest {

    protected abstract LocalTaskService getLocalTaskService();

    /**
     * Simple check to ensure a task can be created.
     */
    @Test
    public void createTask() throws DatabaseException {
        final LocalTaskService localTaskService = getLocalTaskService();
        Assertions.assertNotNull(localTaskService.storeTask(new LocalTask(LocalDateTime.now())));
    }

    /**
     * Simple check to ensure that when we add a new task, it is effectively counted.
     */
    @Test
    void countTasks() throws DatabaseException {
        final LocalTaskService localTaskService = getLocalTaskService();
        Assertions.assertEquals(0, localTaskService.countTasks());
        Assertions.assertNotNull(localTaskService.storeTask(new LocalTask(LocalDateTime.now())));
        Assertions.assertEquals(1, localTaskService.countTasks());
        for (int i = 0; i < 10; i++) {
            localTaskService.storeTask(new LocalTask(LocalDateTime.now()));
        }
        Assertions.assertEquals(11, localTaskService.countTasks());
    }
}
