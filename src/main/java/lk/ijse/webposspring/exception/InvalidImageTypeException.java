package lk.ijse.webposspring.exception;

public class InvalidImageTypeException extends RuntimeException {
    public InvalidImageTypeException(String message) {
        super(message);
    }

    public InvalidImageTypeException(String message, Throwable cause) {
        super(message, cause);
    }

}