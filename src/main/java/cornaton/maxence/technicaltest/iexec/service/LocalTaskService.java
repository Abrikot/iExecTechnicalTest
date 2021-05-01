package cornaton.maxence.technicaltest.iexec.service;

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
     */
    LocalTask storeTask(LocalTask task);

    /**
     * Compute the number of tasks stored in the memory.
     *
     * @return The number of tasks stored in the memory.
     */
    long countTasks();
}
