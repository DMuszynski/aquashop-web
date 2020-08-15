package pl.dmuszynski.aquashop.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserIsAlreadyEnabledException extends RuntimeException {

    private static final long serialVersionUID = 7992904489502842099L;

    public UserIsAlreadyEnabledException() {
        this("User is already enabled!");
    }

    public UserIsAlreadyEnabledException(String message) {
        this(message, null);
    }

    public UserIsAlreadyEnabledException(String message, Throwable cause) {
        super(message, cause);
    }
}
