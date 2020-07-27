package pl.dmuszynski.aquashop.exception;

public class UserSamePasswordException extends RuntimeException {

    public UserSamePasswordException() {
        super("You enter old password");
    }
}
