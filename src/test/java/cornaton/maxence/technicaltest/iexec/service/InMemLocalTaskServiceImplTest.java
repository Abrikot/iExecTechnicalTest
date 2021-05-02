package cornaton.maxence.technicaltest.iexec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This will execute every test of {@link LocalTaskServiceTest} using an in-memory storage.
 */
@SpringBootTest
public class InMemLocalTaskServiceImplTest extends LocalTaskServiceTest {
    @Autowired
    @Qualifier("inMemLocalTaskServiceImpl")
    private LocalTaskService localTaskService;

    @Override
    protected LocalTaskService getTaskService() {
        return localTaskService;
    }
}
