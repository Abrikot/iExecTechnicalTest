package cornaton.maxence.technicaltest.iexec.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

/**
 * Base class for a Task.
 */
public abstract class AbstractTask {
    @Id
    private String id;
    private LocalDateTime date;

    public AbstractTask() {
    }

    public AbstractTask(LocalDateTime date) {
        this.date = date;
    }

    public AbstractTask(String id, LocalDateTime date) {
        this.id = id;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
