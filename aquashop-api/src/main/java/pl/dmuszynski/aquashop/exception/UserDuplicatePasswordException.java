package pl.dmuszynski.aquashop.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserDuplicatePasswordException extends RuntimeException {

    private static final long serialVersionUID = 7992904489502842099L;

    public UserDuplicatePasswordException() {
        this("You enter duplicate password!");
    }

    public UserDuplicatePasswordException(String message) {
        this(message, null);
    }

    public UserDuplicatePasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}