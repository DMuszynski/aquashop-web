package pl.dmuszynski.aquashop.exception;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException() {
        super("Could not find address");
    }
}
