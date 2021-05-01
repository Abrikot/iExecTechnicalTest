package cornaton.maxence.technicaltest.iexec.service;

import cornaton.maxence.technicaltest.iexec.dao.LocalTaskRepository;
import cornaton.maxence.technicaltest.iexec.exceptions.DatabaseException;
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
     * @throws DatabaseException If the database has encountered an exception (e.g. it has been closed).
     */
    @Override
    public LocalTask storeTask(LocalTask task) throws DatabaseException {
        return localTaskRepository.save(task);
    }

    /**
     * Compute the number of tasks stored in the database.
     *
     * @return The number of tasks stored in the database.
     * @throws DatabaseException If the database has encountered an exception (e.g. it has been closed).
     */
    @Override
    public long countTasks() throws DatabaseException {
        return localTaskRepository.count();
    }
}
