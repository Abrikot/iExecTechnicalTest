package cornaton.maxence.technicaltest.iexec.service;

import cornaton.maxence.technicaltest.iexec.model.LocalTask;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemLocalTaskServiceImpl implements LocalTaskService {
    private final List<LocalTask> tasks = new ArrayList<>();

    /**
     * Store the task in an in-memory {@link List}.
     *
     * @param task The {@link LocalTask} to store.
     * @return The exact same {@link LocalTask}.
     */
    @Override
    public LocalTask storeTask(LocalTask task) {
        //noinspection ConstantConditions
        if (tasks.add(task)) {
            return task;
        }
        return null;
    }

    /**
     * Compute the number of tasks stored in the memory by taking the size of the memory-holder {@link List}.
     *
     * @return The number of tasks stored in the memory.
     */
    @Override
    public long countTasks() {
        return tasks.size();
    }
}
