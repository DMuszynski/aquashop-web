package pl.dmuszynski.aquashop.exception;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UniqueEmailException.class)
    public ResponseEntity<Object> handleUniqueEmailException(UniqueEmailException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, ex.getMessage(), HttpHeaders.EMPTY, HttpStatus.CONFLICT, webRequest);
    }

    @ExceptionHandler(UniqueUsernameException.class)
    public ResponseEntity<Object> handleUniqueUsernameException(UniqueUsernameException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, ex.getMessage(), HttpHeaders.EMPTY, HttpStatus.CONFLICT, webRequest);
    }

    @ExceptionHandler(EnabledException.class)
    public ResponseEntity<Object> handleEnabledException(EnabledException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, ex.getMessage(), HttpHeaders.EMPTY, HttpStatus.CONFLICT, webRequest);
    }

    @ExceptionHandler(DuplicatePasswordException.class)
    public ResponseEntity<Object> handleDuplicatePasswordException(DuplicatePasswordException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, ex.getMessage(), HttpHeaders.EMPTY, HttpStatus.CONFLICT, webRequest);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, ex.getMessage(), HttpHeaders.EMPTY, HttpStatus.NOT_FOUND, webRequest);
    }
}
