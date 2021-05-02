package cornaton.maxence.technicaltest.iexec.service;

import cornaton.maxence.technicaltest.iexec.model.LocalTask;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * This will execute every test of {@link LocalTaskServiceTest} using a database storage.
 * Note that it will fail if the database can't be reached.
 */
@SpringBootTest
class DatabaseLocalAbstractTaskServiceImplTest extends LocalTaskServiceTest {
    @Autowired
    @Qualifier("databaseLocalTaskServiceImpl")
    private LocalTaskService localTaskService;
    @Autowired
    private MongoOperations operations;

    public LocalTaskService getTaskService() {
        return localTaskService;
    }

    /**
     * The tests should not work on the same dataset so we drop the collection before each test.
     */
    @BeforeEach
    public void init() {
        operations.dropCollection(LocalTask.class);
    }

}