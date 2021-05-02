package cornaton.maxence.technicaltest.iexec.exceptions;

public class UndefinedPropertyException extends RuntimeException {
    public UndefinedPropertyException(String message) {
        super(message);
    }

    public UndefinedPropertyException(String message, Throwable cause) {
        super(message, cause);
    }

    public UndefinedPropertyException(Throwable cause) {
        super(cause);
    }
}
