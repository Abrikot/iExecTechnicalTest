package cornaton.maxence.technicaltest.iexec.exceptions;

/**
 * Exception describing a failure over a LocalTask creation.
 */
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
