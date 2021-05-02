package cornaton.maxence.technicaltest.iexec.model;

import java.time.LocalDateTime;

public class LocalTask extends AbstractTask {
    public LocalTask() {
    }

    public LocalTask(LocalDateTime date) {
        super(date);
    }

    public LocalTask(String id, LocalDateTime date) {
        super(id, date);
    }
}
