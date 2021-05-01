package cornaton.maxence.technicaltest.iexec.controller;

import cornaton.maxence.technicaltest.iexec.exceptions.DatabaseException;
import cornaton.maxence.technicaltest.iexec.model.LocalTask;
import cornaton.maxence.technicaltest.iexec.service.LocalTaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/local/tasks")
public class LocalTaskController {
    private final static Logger log = LogManager.getLogger(LocalTaskController.class);
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private LocalTaskService localTaskService;

    @Autowired
    public void setLocalTaskService(LocalTaskService localTaskService) {
        this.localTaskService = localTaskService;
    }

    /**
     * Create and store a new {@link LocalTask} based on the current datetime.
     *
     * @return The newly created {@link LocalTask} with a potential new ID.
     * @throws DatabaseException If the storage method is a database and it has encountered an exception (e.g. it has been closed) so the creation has failed.
     */
    @PostMapping
    public LocalTask createTask() throws DatabaseException {
        final LocalDateTime now = LocalDateTime.now();
        final String nowFormatted = now.format(formatter);
        log.info("Creating a new LocalTask on {}...", nowFormatted);
        try {
            final LocalTask createdTask = localTaskService.storeTask(new LocalTask(now));
            log.info("Successfully created a new task on {}.", nowFormatted);
            return createdTask;
        } catch (DatabaseException e) {
            log.error("The LocalTask creation has failed on {}.", nowFormatted);
            throw e;
        }
    }

    /**
     * Compute the total number of {@link LocalTask}s stored in the memory-holder structure.
     * Note that it can take a while if there are a lot of entries.
     *
     * @return The total number of {@link LocalTask}s stored in the memory-holder structure.
     * @throws DatabaseException If the storage method is a database and it has encountered an exception (e.g. it has been closed) so the count has failed.
     */
    @GetMapping("/count")
    public long countTasks() throws DatabaseException {
        try {
            log.info("Counting how many LocalTasks are stored...");
            final long taskNumber = localTaskService.countTasks();
            log.info("{} LocalTask(s) are currently stored.", taskNumber);
            return taskNumber;
        } catch (DatabaseException e) {
            log.error("The LocalTask count has failed.");
            throw e;
        }
    }
}
