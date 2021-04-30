package cornaton.maxence.technicaltest.iexec.model;

import java.time.LocalDateTime;

public class LocalTask {
    private Long id;
    private LocalDateTime date;

    public LocalTask() {
    }

    public LocalTask(LocalDateTime date) {
        this.date = date;
    }

    public LocalTask(Long id, LocalDateTime date) {
        this.id = id;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
