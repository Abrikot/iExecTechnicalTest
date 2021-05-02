package cornaton.maxence.technicaltest.iexec.service;

import cornaton.maxence.technicaltest.iexec.dao.TaskRepository;
import cornaton.maxence.technicaltest.iexec.exceptions.BlockchainException;
import cornaton.maxence.technicaltest.iexec.model.BlockchainTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.generated.contracts.TaskCounter;

import java.math.BigInteger;

/**
 * Service to handle every {@link BlockchainTask} action.
 */
@Service
public class BlockchainTaskServiceImpl implements BlockchainTaskService {
    private TaskCounter taskCounter;
    private TaskRepository<BlockchainTask> taskRepository;

    @Autowired
    public void setTaskRepository(TaskRepository<BlockchainTask> blockchainTaskRepository) {
        this.taskRepository = blockchainTaskRepository;
    }

    @Autowired
    public void setTaskCounter(TaskCounter taskCounter) {
        this.taskCounter = taskCounter;
    }

    @Override
    public BlockchainTask storeTask(BlockchainTask task) throws BlockchainException {
        // Two steps:
        // 1. Store the task inside the blockchain
        // 2. Store the task inside the database
        try {
            final BigInteger transactionIndex = taskCounter.newTask().send().getTransactionIndex();
            final BlockchainTask blockchainTask = new BlockchainTask(transactionIndex.toString(), task.getDate());
            taskRepository.save(blockchainTask);
            return blockchainTask;
        } catch (Exception e) {
            throw new BlockchainException("The BlockchainTask creation has failed.", e);
        }
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
