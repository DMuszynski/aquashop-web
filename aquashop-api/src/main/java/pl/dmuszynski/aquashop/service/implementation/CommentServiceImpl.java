package pl.dmuszynski.aquashop.service.implementation;

import pl.dmuszynski.aquashop.exception.CommentNotFoundException;
import pl.dmuszynski.aquashop.exception.ProductNotFoundException;
import pl.dmuszynski.aquashop.repository.CommentRepository;
import pl.dmuszynski.aquashop.repository.ProductRepository;
import pl.dmuszynski.aquashop.service.CommentService;
import pl.dmuszynski.aquashop.model.Comment;
import pl.dmuszynski.aquashop.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service(value = "commentService")
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ProductRepository productRepository) {
        this.commentRepository = commentRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Comment addProductComment(Comment comment, Long productId) {
        final Product product = this.productRepository.findById(productId)
            .orElseThrow(ProductNotFoundException::new);

        return this.commentRepository.save(
            new Comment(product,
                comment.getDescription(),
                comment.getMark()
            )
        );
    }

    @Override
    public Comment updateDescriptionById(String description, Long id) {
        final Comment comment = this.commentRepository.findById(id)
            .orElseThrow(CommentNotFoundException::new);
        comment.setDescription(description);

        this.commentRepository.updateDescriptionById(comment.getDescription(), id);
        return comment;
    }

    @Override
    public Comment updateMarkById(int mark, Long id) {
        final Comment comment = this.commentRepository.findById(id)
            .orElseThrow(CommentNotFoundException::new);
        comment.setMark(mark);

        this.commentRepository.updateMarkById(comment.getMark(), id);
        return comment;
    }

    @Override
    public void deleteById(Long id) {
        this.commentRepository.deleteById(id);
    }
}
