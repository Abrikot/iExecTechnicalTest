package cornaton.maxence.technicaltest.iexec.dao;

import com.mongodb.client.MongoCollection;
import cornaton.maxence.technicaltest.iexec.model.LocalTask;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
@Profile("mongodb")
public class MongoDbLocalTaskRepository implements LocalTaskRepository {
    private final MongoOperations operations;

    @Autowired
    public MongoDbLocalTaskRepository(MongoOperations operations) {
        Assert.notNull(operations, "MongoOperations can't be null.");
        this.operations = operations;
    }

    /**
     * Save the {@link LocalTask} inside the collection {@code LocalTask} of the database.
     *
     * @param localTask The {@link LocalTask} to store.
     * @param <S>       A {@link LocalTask}-based class.
     * @return The stored {@link LocalTask} with a newly created ID.
     */
    @Override
    public <S extends LocalTask> S save(S localTask) {
        return operations.save(localTask);
    }

    /**
     * Compute the number of {@link LocalTask}s stored in the collection {@code LocalTask} of the database.
     * Note that we compute the exact number of {@link LocalTask}s stored in the database so it can take a while
     * if the dataset is big.
     *
     * @return The number of tasks stored in the database.
     */
    @Override
    public long count() {
        final String collectionName = operations.getCollectionName(LocalTask.class);
        MongoCollection<Document> collection = operations.getCollection(collectionName);
        return collection.countDocuments();
    }
}