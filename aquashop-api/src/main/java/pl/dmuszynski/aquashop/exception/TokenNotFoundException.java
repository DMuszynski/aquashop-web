package pl.dmuszynski.aquashop.exception;

public class TokenNotFoundException extends RuntimeException {

    public TokenNotFoundException(String value) {
        super("Could not found Token: " + value);
    }
}
