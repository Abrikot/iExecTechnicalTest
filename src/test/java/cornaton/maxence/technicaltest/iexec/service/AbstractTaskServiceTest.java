package cornaton.maxence.technicaltest.iexec.service;

import cornaton.maxence.technicaltest.iexec.AbstractBaseTests;
import cornaton.maxence.technicaltest.iexec.exceptions.IexecAbstractException;
import cornaton.maxence.technicaltest.iexec.model.AbstractTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.function.Function;

public abstract class AbstractTaskServiceTest<T extends AbstractTask> extends AbstractBaseTests {
    private final Function<LocalDateTime, T> taskConstructor;

    public AbstractTaskServiceTest(Function<LocalDateTime, T> taskConstructor) {
        this.taskConstructor = taskConstructor;
    }

    protected abstract TaskService<T> getTaskService();

    protected abstract long getBaseCount();

    /**
     * Simple check to ensure a task can be created.
     */
    @Test
    public void createTask() throws IexecAbstractException {
        final TaskService<T> taskService = getTaskService();
        Assertions.assertNotNull(taskService.storeTask(taskConstructor.apply(LocalDateTime.now())));
    }

    /**
     * Simple check to ensure that when we add a new task, it is effectively counted.
     */
    @Test
    void countTasks() throws IexecAbstractException {
        final TaskService<T> taskService = getTaskService();
        final long baseCount = getBaseCount();
        Assertions.assertEquals(baseCount, taskService.countTasks());
        Assertions.assertNotNull(taskService.storeTask(taskConstructor.apply(LocalDateTime.now())));
        Assertions.assertEquals(baseCount + 1, taskService.countTasks());
        for (int i = 0; i < 10; i++) {
            taskService.storeTask(taskConstructor.apply(LocalDateTime.now()));
        }
        Assertions.assertEquals(baseCount + 11, taskService.countTasks());
    }
}
