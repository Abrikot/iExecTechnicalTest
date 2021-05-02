package cornaton.maxence.technicaltest.iexec.exceptions;

/**
 * Base class for exception in this project. This enables a better exception handling.
 * <br>
 * Note that this class must be inherited so the exceptions are precise and their handling is easier.
 */
public abstract class IexecAbstractException extends Exception {
    public IexecAbstractException(String message) {
        super(message);
    }

    public IexecAbstractException(String message, Throwable cause) {
        super(message, cause);
    }

    public IexecAbstractException(Throwable cause) {
        super(cause);
    }
}
