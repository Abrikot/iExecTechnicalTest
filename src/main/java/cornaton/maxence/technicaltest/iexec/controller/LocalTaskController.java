package cornaton.maxence.technicaltest.iexec.controller;

import cornaton.maxence.technicaltest.iexec.exceptions.LocalTaskCreationException;
import cornaton.maxence.technicaltest.iexec.model.LocalTask;
import cornaton.maxence.technicaltest.iexec.service.LocalTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/local/tasks")
public class LocalTaskController {
    private LocalTaskService localTaskService;

    @Autowired
    public void setLocalTaskService(LocalTaskService localTaskService) {
        this.localTaskService = localTaskService;
    }

    /**
     * Create and store a new {@link LocalTask} based on the current datetime.
     *
     * @return The newly created {@link LocalTask} with a potential new ID.
     * @throws LocalTaskCreationException when the task creation has failed. It can be due to a faulty database.
     */
    @PostMapping
    public LocalTask createTask() throws LocalTaskCreationException {
        final LocalTask localTask = new LocalTask(LocalDateTime.now());
        final LocalTask createdTask = localTaskService.storeTask(localTask);
        if (createdTask != null) {
            return localTask;
        } else {
            throw new LocalTaskCreationException("The LocalTask creation has failed.");
        }
    }

    /**
     * Compute the total number of {@link LocalTask}s stored in the memory-holder structure.
     * Note that it can take a while if there are a lot of entries.
     *
     * @return The total number of {@link LocalTask}s stored in the memory-holder structure.
     */
    @GetMapping("/count")
    public long countTasks() {
        return localTaskService.countTasks();
    }
}
