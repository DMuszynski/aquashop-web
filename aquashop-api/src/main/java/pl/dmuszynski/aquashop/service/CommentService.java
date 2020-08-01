package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.Comment;

public interface CommentService {
    void addProductComment(Comment comment, Long productId);
    void updateDescriptionById(String description, Long id);
    void updateMarkById(int mark, Long id);
    void deleteById(Long id);
}
