package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.payload.dto.CommentDTO;

public interface CommentService {
    CommentDTO addProductComment(CommentDTO commentDetails, Long productId);
    CommentDTO updateComment(CommentDTO commentDetails, Long id);
    void deleteById(Long id);
}
