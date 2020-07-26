package pl.dmuszynski.aquashop.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<Object> handleTokenNotFoundException(TokenNotFoundException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, ex.getMessage(), HttpHeaders.EMPTY, HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(UserEmailAlreadyExistException.class)
    public ResponseEntity<Object> handleUserEmailAlreadyExistException(UserEmailAlreadyExistException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, ex.getMessage(), HttpHeaders.EMPTY, HttpStatus.CONFLICT, webRequest);
    }

    @ExceptionHandler(UserIsAlreadyEnabledException.class)
    public ResponseEntity<Object> handleUserIsAlreadyEnabledException(UserIsAlreadyEnabledException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, ex.getMessage(), HttpHeaders.EMPTY, HttpStatus.CONFLICT, webRequest);
    }
}
