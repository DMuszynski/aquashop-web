package pl.dmuszynski.aquashop.exception;

public class UserEmailAlreadyExistException extends RuntimeException {

    public UserEmailAlreadyExistException(String value) {
        super("The user with the given e-mail address: " + value + " is already exists");
    }
}
