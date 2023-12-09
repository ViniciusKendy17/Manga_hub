package manga_hub.manga_hub.exceptions;

public class NotFoundExpetion extends RuntimeException{
    public NotFoundExpetion(String message) {
        super(message);
    }

    public NotFoundExpetion(String message, Throwable cause) {
        super(message, cause);
    }
}
