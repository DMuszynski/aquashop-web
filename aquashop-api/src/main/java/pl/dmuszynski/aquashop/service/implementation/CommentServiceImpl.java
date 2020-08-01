package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmuszynski.aquashop.repository.CommentRepository;
import pl.dmuszynski.aquashop.service.CommentService;
import pl.dmuszynski.aquashop.model.Comment;

import javax.transaction.Transactional;

@Service @Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void addProductComment(Comment comment, Long productId) {

        commentRepository.save(comment);
    }

    @Override
    public void updateDescriptionById(String description, Long id) {
        this.commentRepository.updateDescriptionById(description, id);
    }

    @Override
    public void updateMarkById(int mark, Long id) {
        this.commentRepository.updateMarkById(mark, id);
    }

    @Override
    public void deleteById(Long id) {
        this.commentRepository.deleteById(id);
    }
}
