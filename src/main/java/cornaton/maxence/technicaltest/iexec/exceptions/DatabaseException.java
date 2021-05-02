package cornaton.maxence.technicaltest.iexec.exceptions;

/**
 * Exception describing a database failure.
 * It can denote an unreachable database, a fail during an insertion, ...
 */
public class DatabaseException extends IexecAbstractException {
    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }
}
