package pl.dmuszynski.aquashop.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("Could not find product by " + id + "id");
    }
}