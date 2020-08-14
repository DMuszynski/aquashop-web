package pl.dmuszynski.aquashop.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super("Could not find product");
    }
}