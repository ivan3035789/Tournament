package exiption;

public class NotRegisteredException extends RuntimeException {
    String message;

    public NotRegisteredException(String message) {
        super(message);
    }
}
