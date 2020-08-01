package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmuszynski.aquashop.repository.CommentRepository;
import pl.dmuszynski.aquashop.service.CommentService;
import pl.dmuszynski.aquashop.service.ProductService;
import pl.dmuszynski.aquashop.model.Comment;
import pl.dmuszynski.aquashop.model.Product;

import javax.transaction.Transactional;

@Service @Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ProductService productService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ProductService productService) {
        this.commentRepository = commentRepository;
        this.productService = productService;
    }

    @Override
    public void addProductComment(Comment comment, Long productId) {
        final Product product = this.productService.findById(productId);
        comment.setProduct(product);

        this.commentRepository.save(comment);
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
