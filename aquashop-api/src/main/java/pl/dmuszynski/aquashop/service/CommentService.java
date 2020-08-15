package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.Comment;

public interface CommentService {
    Comment addProductComment(Comment comment, Long productId);
    Comment updateComment(Comment comment, Long id);
    Comment findById(Long id);
    void deleteById(Long id);
}
