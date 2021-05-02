package cornaton.maxence.technicaltest.iexec.controller;

import cornaton.maxence.technicaltest.iexec.model.BlockchainTask;
import cornaton.maxence.technicaltest.iexec.service.BlockchainTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller inheriting the {@link AbstractTaskController} to provide an implementation for the {@link BlockchainTask} objects.
 */
@RestController
@RequestMapping("/blockchain/tasks")
public class BlockchainTaskController extends AbstractTaskController<BlockchainTask> {
    @Autowired
    public BlockchainTaskController(BlockchainTaskService blockchainTaskService) {
        super(BlockchainTask.class, BlockchainTask::new, blockchainTaskService);
    }
}
