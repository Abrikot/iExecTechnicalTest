package cornaton.maxence.technicaltest.iexec.service;

import cornaton.maxence.technicaltest.iexec.exceptions.DatabaseException;
import cornaton.maxence.technicaltest.iexec.model.LocalTask;

/**
 * Interface to handle every local task actions:
 * <ul>
 *     <li>Create a local task</li>
 *     <li>Compute the number of local tasks</li>
 * </ul>
 */
public interface LocalTaskService {
    /**
     * Store the task in the defined memory holder.
     *
     * @param task The {@link LocalTask} to store.
     * @return The {@link LocalTask} with a potentially newly created ID.
     * @throws DatabaseException If the storage method is a database and it has encountered an exception (e.g. it has been closed).
     */
    LocalTask storeTask(LocalTask task) throws DatabaseException;

    /**
     * Compute the number of tasks stored in the memory.
     *
     * @return The number of tasks stored in the memory.
     * @throws DatabaseException If the storage method is a database and it has encountered an exception (e.g. it has been closed).
     */
    long countTasks() throws DatabaseException;
}
