package lk.ijse.webposspring.exception;

public class DataPersistFailedException extends RuntimeException {
    public DataPersistFailedException(String message) {
        super(message);
    }

    public DataPersistFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}