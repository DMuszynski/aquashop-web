package pl.dmuszynski.aquashop.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("Could not found User: " + id);
    }
}
