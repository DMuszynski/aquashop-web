package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.Comment;

public interface CommentService {
    Comment addProductComment(Comment comment, Long productId);
    Comment updateDescriptionById(String description, Long id);
    Comment updateMarkById(int mark, Long id);
    void deleteById(Long id);
}
