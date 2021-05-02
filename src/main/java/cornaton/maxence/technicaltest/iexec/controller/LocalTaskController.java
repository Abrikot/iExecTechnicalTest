package cornaton.maxence.technicaltest.iexec.controller;

import cornaton.maxence.technicaltest.iexec.model.LocalTask;
import cornaton.maxence.technicaltest.iexec.service.LocalTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller inheriting the {@link AbstractTaskController} to provide an implementation for the {@link LocalTask} objects.
 */
@RestController
@RequestMapping("/local/tasks")
public class LocalTaskController extends AbstractTaskController<LocalTask> {
    @Autowired
    public LocalTaskController(LocalTaskService localTaskService) {
        super(LocalTask.class, LocalTask::new, localTaskService);
    }
}
