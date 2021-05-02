package cornaton.maxence.technicaltest.iexec.service;

import cornaton.maxence.technicaltest.iexec.model.BlockchainTask;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This will execute every test of {@link BlockchainTaskService}.
 * <p>
 * /!\ These tests are currently disabled as the required stack has not been deployed.
 * It'll probably need a Ganache local/Docker app to run.
 */
@SpringBootTest
@Disabled
class BlockchainTaskServiceImplTest extends AbstractTaskServiceTest<BlockchainTask> {
    @Autowired
    @Qualifier("blockchainTaskService")
    private BlockchainTaskService taskService;

    public BlockchainTaskServiceImplTest() {
        super(BlockchainTask::new);
    }

    @Override
    protected TaskService<BlockchainTask> getTaskService() {
        return taskService;
    }

    @Override
    protected long getBaseCount() {
        return 0;
    }
}