package pl.dmuszynski.aquashop.exception;

public class UserIsAlreadyEnabledException extends RuntimeException {

    public UserIsAlreadyEnabledException() {
        super("User is already enabled !" );
    }
}
