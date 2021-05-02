package cornaton.maxence.technicaltest.iexec.service;

import cornaton.maxence.technicaltest.iexec.exceptions.BlockchainException;
import cornaton.maxence.technicaltest.iexec.model.BlockchainTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.generated.contracts.TaskCounter;

/**
 * Service to handle every {@link BlockchainTask} action.
 */
@Service
public class BlockchainTaskServiceImpl implements BlockchainTaskService {
    private TaskCounter taskCounter;

    @Autowired
    public void setTaskCounter(TaskCounter taskCounter) {
        this.taskCounter = taskCounter;
    }

    @Override
    public BlockchainTask storeTask(BlockchainTask task) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long countTasks() throws BlockchainException {
        try {
            return taskCounter.taskCount().send().longValue();
        } catch (Exception e) {
            throw new BlockchainException("The BlockchainTask count has failed.", e);
        }
    }
}
