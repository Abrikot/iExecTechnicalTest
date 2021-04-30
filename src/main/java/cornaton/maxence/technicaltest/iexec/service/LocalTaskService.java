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
    boolean createTask(LocalTask task);

    long countTasks();
}
