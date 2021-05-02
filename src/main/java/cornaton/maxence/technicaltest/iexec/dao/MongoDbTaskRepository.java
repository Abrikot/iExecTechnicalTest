package cornaton.maxence.technicaltest.iexec.dao;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import cornaton.maxence.technicaltest.iexec.exceptions.DatabaseException;
import cornaton.maxence.technicaltest.iexec.model.AbstractTask;
import cornaton.maxence.technicaltest.iexec.model.LocalTask;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.util.Assert;

/**
 * Repository handling MongoDB operations on {@link AbstractTask} objects.
 *
 * @param <T> Type of the task.
 */
@Profile("mongodb")
public abstract class MongoDbTaskRepository<T extends AbstractTask> implements TaskRepository<T> {
    private final MongoOperations operations;
    private final Class<T> taskType;

    @Autowired
    public MongoDbTaskRepository(Class<T> taskType, MongoOperations operations) {
        this.taskType = taskType;
        Assert.notNull(operations, "MongoOperations can't be null.");
        this.operations = operations;
    }

    /**
     * Save the {@link LocalTask} inside the collection {@code LocalTask} of the database.
     *
     * @param task The {@link LocalTask} to store.
     * @return The stored {@link LocalTask} with a newly created ID.
     * @throws DatabaseException If the database has encountered an exception (e.g. it has been closed).
     */
    @Override
    public T save(T task) throws DatabaseException {
        try {
            return operations.save(task);
        } catch (MongoException e) {
            throw new DatabaseException("The task creation has failed.", e);
        }
    }

    /**
     * Compute the number of {@link LocalTask}s stored in the collection {@code LocalTask} of the database.
     * Note that we compute the exact number of {@link LocalTask}s stored in the database so it can take a while
     * if the dataset is big.
     *
     * @return The number of tasks stored in the database.
     * @throws DatabaseException If the database has encountered an exception (e.g. it has been closed).
     */
    @Override
    public long count() throws DatabaseException {
        try {
            final String collectionName = operations.getCollectionName(taskType);
            MongoCollection<Document> collection = operations.getCollection(collectionName);
            return collection.countDocuments();
        } catch (MongoException e) {
            throw new DatabaseException("The task count has failed.", e);
        }
    }
}
