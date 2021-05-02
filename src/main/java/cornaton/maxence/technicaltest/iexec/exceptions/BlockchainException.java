package cornaton.maxence.technicaltest.iexec.exceptions;

/**
 * Exception describing a failure while using the blockchain.
 * It can denote an unreachable blockchain, a fail during a write, ...
 */
public class BlockchainException extends IexecAbstractException {
    public BlockchainException(String message) {
        super(message);
    }

    public BlockchainException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlockchainException(Throwable cause) {
        super(cause);
    }
}
