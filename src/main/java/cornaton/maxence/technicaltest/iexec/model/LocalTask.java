package cornaton.maxence.technicaltest.iexec.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class LocalTask {

    @Id
    private String id;
    private LocalDateTime date;

    public LocalTask() {
    }

    public LocalTask(LocalDateTime date) {
        this.date = date;
    }

    public LocalTask(String id, LocalDateTime date) {
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
