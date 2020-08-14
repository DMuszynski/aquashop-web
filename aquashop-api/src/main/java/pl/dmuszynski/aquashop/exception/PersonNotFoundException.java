package pl.dmuszynski.aquashop.exception;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException() {
        super("Could not find person");
    }
}