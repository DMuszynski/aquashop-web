package pl.dmuszynski.aquashop.exception;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(Long id) {
        super("Could not find address by " + id + "id");
    }
}
