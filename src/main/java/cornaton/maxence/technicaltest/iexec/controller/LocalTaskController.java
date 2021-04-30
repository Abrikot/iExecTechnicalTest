package cornaton.maxence.technicaltest.iexec.controller;

import cornaton.maxence.technicaltest.iexec.exceptions.LocalTaskCreationException;
import cornaton.maxence.technicaltest.iexec.model.LocalTask;
import cornaton.maxence.technicaltest.iexec.service.LocalTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/local/tasks")
public class LocalTaskController {
    private LocalTaskService localTaskService;

    @Autowired
    public void setLocalTaskService(LocalTaskService localTaskService) {
        this.localTaskService = localTaskService;
    }

    /**
     * Create and store a new {@link LocalTask} based on the request body.
     *
     * @param localTask Object to store.
     * @return The newly created {@link LocalTask}.
     * @throws LocalTaskCreationException when the task creation has failed. It can be due to a faulty database.
     */
    @PostMapping
    public LocalTask createTask(@RequestBody LocalTask localTask) throws LocalTaskCreationException {
        boolean created = localTaskService.createTask(localTask);
        if (created) {
            return localTask;
        } else {
            throw new LocalTaskCreationException("The LocalTask creation has failed.");
        }
    }

    @GetMapping("/count")
    public long countTasks() {
        return localTaskService.countTasks();
    }
}
