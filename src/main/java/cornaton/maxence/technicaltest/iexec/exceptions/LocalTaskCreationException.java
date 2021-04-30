package cornaton.maxence.technicaltest.iexec.exceptions;

public class LocalTaskCreationException extends Exception {
    public LocalTaskCreationException(String message) {
        super(message);
    }

    public LocalTaskCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocalTaskCreationException(Throwable cause) {
        super(cause);
    }
}
