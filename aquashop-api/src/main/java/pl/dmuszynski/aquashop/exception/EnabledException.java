package pl.dmuszynski.aquashop.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EnabledException extends RuntimeException {

    private static final long serialVersionUID = 7992904489502842099L;

    public EnabledException() {
        this("User is already enabled!");
    }

    public EnabledException(String message) {
        this(message, null);
    }

    public EnabledException(String message, Throwable cause) {
        super(message, cause);
    }
}
