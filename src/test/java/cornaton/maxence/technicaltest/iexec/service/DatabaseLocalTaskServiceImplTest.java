package cornaton.maxence.technicaltest.iexec.service;

import cornaton.maxence.technicaltest.iexec.model.LocalTask;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * This will execute all the tests of {@link LocalTaskServiceTest} using a database storage.
 */

@SpringBootTest
class DatabaseLocalTaskServiceImplTest extends LocalTaskServiceTest {
    @Autowired
    @Qualifier("databaseLocalTaskServiceImpl")
    private LocalTaskService localTaskService;
    @Autowired
    private MongoOperations operations;

    public LocalTaskService getLocalTaskService() {
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