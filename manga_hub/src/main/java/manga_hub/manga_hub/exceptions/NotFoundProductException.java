package manga_hub.manga_hub.exceptions;

public class NotFoundProductException extends RuntimeException{
    public NotFoundProductException(String message) {
        super(message);
    }

    public NotFoundProductException(String message, Throwable cause) {
        super(message, cause);
    }
}
