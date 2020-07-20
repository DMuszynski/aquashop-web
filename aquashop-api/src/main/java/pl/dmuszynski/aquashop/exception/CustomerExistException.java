package pl.dmuszynski.aquashop.exception;

public class CustomerExistException extends RuntimeException {

    public CustomerExistException(String value) {
        super("The user with the given e-mail address: " + value + " is already exists");
    }
}
