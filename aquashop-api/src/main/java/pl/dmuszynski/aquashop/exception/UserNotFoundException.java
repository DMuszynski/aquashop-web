package pl.dmuszynski.aquashop.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Could not found User");
    }
}
