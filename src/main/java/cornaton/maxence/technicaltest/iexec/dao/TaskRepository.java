package cornaton.maxence.technicaltest.iexec.dao;

import cornaton.maxence.technicaltest.iexec.exceptions.DatabaseException;
import cornaton.maxence.technicaltest.iexec.model.AbstractTask;
import org.springframework.data.repository.Repository;

/**
 * Repository interface to access {@link AbstractTask}s-based objects.
 */
public interface TaskRepository<T extends AbstractTask> extends Repository<T, Long> {
    /**
     * Save the {@link AbstractTask} inside the database.
     *
     * @param task The {@link AbstractTask} to store.
     * @return The newly-stored {@link AbstractTask}.
     */
    T save(T task) throws DatabaseException;

    /**
     * Compute the number of {@link AbstractTask} of the given type stored in the database.
     *
     * @return The number of tasks stored in the database.
     */
    long count() throws DatabaseException;
}
