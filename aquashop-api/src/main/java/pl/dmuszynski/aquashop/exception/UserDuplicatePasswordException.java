package pl.dmuszynski.aquashop.exception;

public class UserDuplicatePasswordException extends RuntimeException {

    public UserDuplicatePasswordException() {
        super("You enter old password");
    }
}