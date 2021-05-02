package cornaton.maxence.technicaltest.iexec.service;

import cornaton.maxence.technicaltest.iexec.exceptions.IexecAbstractException;
import cornaton.maxence.technicaltest.iexec.model.AbstractTask;

/**
 * Common service interface to handle every task action:
 * <ul>
 *     <li>Create a task</li>
 *     <li>Compute the number of tasks</li>
 * </ul>
 *
 * @param <T> Class of the task. It is mandatory that this class is based on {@link AbstractTask}.
 */
public interface TaskService<T extends AbstractTask> {
    /**
     * Store the task in the defined memory holder.
     * Note that it can take some time to execute depending on the memory-holder type.
     *
     * @param task The {@link AbstractTask} to store.
     * @return The {@link AbstractTask} with a potentially newly created ID.
     * @throws IexecAbstractException If the storage has encountered an error (e.g. it has been closed or it is unreachable).
     */
    T storeTask(T task) throws IexecAbstractException;

    /**
     * Compute the number of tasks stored in the memory.
     * Note that it can take some time to execute depending on the memory-holder type.
     *
     * @return The number of tasks stored in the memory.
     * @throws IexecAbstractException If the storage has encountered an error (e.g. it has been closed or it is unreachable).
     */
    long countTasks() throws IexecAbstractException;
}
