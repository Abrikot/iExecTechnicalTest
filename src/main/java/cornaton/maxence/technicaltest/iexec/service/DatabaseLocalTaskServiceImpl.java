package cornaton.maxence.technicaltest.iexec.service;

import cornaton.maxence.technicaltest.iexec.dao.LocalTaskRepository;
import cornaton.maxence.technicaltest.iexec.model.LocalTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseLocalTaskServiceImpl implements LocalTaskService {
    private LocalTaskRepository localTaskRepository;

    @Autowired
    public void setLocalTaskRepository(LocalTaskRepository localTaskRepository) {
        this.localTaskRepository = localTaskRepository;
    }

    /**
     * Store the task in an the database.
     *
     * @param task The {@link LocalTask} to store.
     * @return The {@link LocalTask} with a potentially newly created ID.
     */
    @Override
    public LocalTask storeTask(LocalTask task) {
        return localTaskRepository.save(task);
    }

    /**
     * Compute the number of tasks stored in the database.
     *
     * @return The number of tasks stored in the database.
     */
    @Override
    public long countTasks() {
        return localTaskRepository.count();
    }
}
