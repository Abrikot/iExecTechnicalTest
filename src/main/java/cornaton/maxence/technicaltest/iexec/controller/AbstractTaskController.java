package cornaton.maxence.technicaltest.iexec.controller;

import cornaton.maxence.technicaltest.iexec.exceptions.IexecAbstractException;
import cornaton.maxence.technicaltest.iexec.model.AbstractTask;
import cornaton.maxence.technicaltest.iexec.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

/**
 * Define a common abstract class to handle requests on all {@link AbstractTask}-based objects.
 *
 * @param <T> Type of the {@link AbstractTask}s.
 */
public abstract class AbstractTaskController<T extends AbstractTask> {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    protected final Logger log = LogManager.getLogger(this.getClass());

    /**
     * Task service related to the task type.
     */
    private final TaskService<T> taskService;

    /**
     * Reference to a task constructor allowing to build a task object.
     * This is generally used on the task creation endpoint.
     */
    private final Function<LocalDateTime, T> taskConstructor;

    /**
     * Real type of the tasks.
     */
    private final String taskTypeName;

    protected AbstractTaskController(Class<T> taskType, Function<LocalDateTime, T> taskConstructor, TaskService<T> taskService) {
        this.taskTypeName = taskType.getSimpleName();
        this.taskService = taskService;
        this.taskConstructor = taskConstructor;
    }

    /**
     * Compute the total number of tasks stored in the defined memory holder.
     *
     * @return The total number of tasks stored in the defined memory holder.
     */
    @PostMapping
    public T createTask() throws Exception {
        final LocalDateTime now = LocalDateTime.now();
        final String nowFormatted = now.format(formatter);
        log.info("Creating a new {} at {}...", taskTypeName, nowFormatted);
        try {
            final T createdTask = taskService.storeTask(taskConstructor.apply(now));
            log.info("Successfully created a new {} at {}.", taskTypeName, nowFormatted);
            return createdTask;
        } catch (IexecAbstractException e) {
            log.error("The {} creation has failed at {}.", taskTypeName, nowFormatted);
            throw e;
        }
    }

    /**
     * Compute the total number of tasks stored in the defined memory holder.
     *
     * @return The total number of tasks stored in the defined memory holder.
     */
    @GetMapping("/count")
    public long countTasks() throws Exception {
        try {
            log.info("Counting how many {}s are stored...", taskTypeName);
            final long taskNumber = taskService.countTasks();
            log.info("{} {}(s) are currently stored.", taskNumber, taskTypeName);
            return taskNumber;
        } catch (Exception e) {
            log.error("The {} count has failed.", taskTypeName);
            throw e;
        }
    }
}
