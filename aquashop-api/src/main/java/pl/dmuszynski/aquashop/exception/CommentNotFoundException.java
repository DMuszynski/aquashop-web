package pl.dmuszynski.aquashop.exception;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException() {
        super("Could not find comment");
    }
}