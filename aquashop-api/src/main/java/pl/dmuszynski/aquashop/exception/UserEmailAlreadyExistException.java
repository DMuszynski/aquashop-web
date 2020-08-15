package pl.dmuszynski.aquashop.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserEmailAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 7992904489502842099L;

    public UserEmailAlreadyExistException(String message) {
        this(message, null);
    }

    public UserEmailAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
