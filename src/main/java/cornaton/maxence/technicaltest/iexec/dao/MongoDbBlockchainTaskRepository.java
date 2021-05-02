package cornaton.maxence.technicaltest.iexec.dao;

import cornaton.maxence.technicaltest.iexec.model.BlockchainTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

/**
 * Repository inheriting the {@link MongoDbTaskRepository} to provide an implementation for {@link BlockchainTask} objects.
 */
@Repository
public class MongoDbBlockchainTaskRepository extends MongoDbTaskRepository<BlockchainTask> {
    @Autowired
    public MongoDbBlockchainTaskRepository(MongoOperations operations) {
        super(BlockchainTask.class, operations);
    }
}
