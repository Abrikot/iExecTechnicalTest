package cornaton.maxence.technicaltest.iexec.dao;

import cornaton.maxence.technicaltest.iexec.model.LocalTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

/**
 * Repository inheriting the {@link MongoDbTaskRepository} to provide an implementation for {@link LocalTask} objects.
 */
@Repository
public class MongoDbLocalTaskRepository extends MongoDbTaskRepository<LocalTask> {
    @Autowired
    public MongoDbLocalTaskRepository(MongoOperations operations) {
        super(LocalTask.class, operations);
    }
}
