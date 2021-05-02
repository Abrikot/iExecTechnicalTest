package cornaton.maxence.technicaltest.iexec.model;

import java.time.LocalDateTime;

public class BlockchainTask extends AbstractTask {
    public BlockchainTask() {
    }

    public BlockchainTask(LocalDateTime date) {
        super(date);
    }

    public BlockchainTask(String id, LocalDateTime date) {
        super(id, date);
    }
}
