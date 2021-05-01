package cornaton.maxence.technicaltest.iexec.dao;

import cornaton.maxence.technicaltest.iexec.exceptions.DatabaseException;
import cornaton.maxence.technicaltest.iexec.model.LocalTask;
import org.springframework.data.repository.Repository;

/**
 * Repository interface to access {@link LocalTask}s.
 */
public interface LocalTaskRepository extends Repository<LocalTask, Long> {
    /**
     * Save the {@link LocalTask} inside the database.
     *
     * @param localTask The {@link LocalTask} to store.
     * @param <S>       A {@link LocalTask}-based class.
     * @return The stored {@link LocalTask} with a potentially newly created ID.
     */
    <S extends LocalTask> S save(S localTask) throws DatabaseException;

    /**
     * Compute the number of {@link LocalTask} stored in the database.
     *
     * @return The number of tasks stored in the database.
     */
    long count() throws DatabaseException;
}
