package pl.dmuszynski.aquashop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UsernameAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 7992904489502842099L;

    public UsernameAlreadyExistException(String message) {
        this(message, null);
    }

    public UsernameAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}