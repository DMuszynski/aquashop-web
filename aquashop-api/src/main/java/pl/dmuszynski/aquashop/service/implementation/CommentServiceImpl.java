package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.repository.CommentRepository;
import pl.dmuszynski.aquashop.service.CommentService;
import pl.dmuszynski.aquashop.service.ProductService;
import pl.dmuszynski.aquashop.model.Comment;
import pl.dmuszynski.aquashop.model.Product;

@Service(value = "commentService")
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ProductService productService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ProductService productService) {
        this.commentRepository = commentRepository;
        this.productService = productService;
    }

    @Override
    public Comment addProductComment(Comment comment, Long productId) {
        final Product product = this.productService.findById(productId);
        return this.commentRepository
            .save(new Comment(product, comment.getDescription(), comment.getRating()));
    }

    @Override
    public Comment updateComment(Comment commentDetails, Long id) {
        final Comment comment = this.findById(id);
        return this.commentRepository
            .save(new Comment(comment.getProduct(), comment.getId(),
                commentDetails.getDescription(), commentDetails.getRating()));
    }

    @Override
    public Comment findById(Long id) throws ResourceNotFoundException {
        return this.commentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Comment not found for id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        this.commentRepository.deleteById(id);
    }
}
