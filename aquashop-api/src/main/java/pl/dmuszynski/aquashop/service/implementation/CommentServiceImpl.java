package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import pl.dmuszynski.aquashop.repository.CommentRepository;
import pl.dmuszynski.aquashop.payload.CommentDTO;
import pl.dmuszynski.aquashop.service.CommentService;
import pl.dmuszynski.aquashop.service.ProductService;
import pl.dmuszynski.aquashop.model.Comment;
import pl.dmuszynski.aquashop.model.Product;

@Service(value = "commentService")
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ProductService productService, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDTO addProductComment(CommentDTO commentDetails, Long productId) {
        final Product foundProduct = this.productService.findProductById(productId);
        final Comment savedProduct = this.commentRepository
            .save(new Comment(
                foundProduct,
                commentDetails.getDescription(),
                commentDetails.getRating())
            );

        return this.modelMapper.map(savedProduct, CommentDTO.class);
    }

    @Override
    public CommentDTO updateComment(CommentDTO commentDetails, Long commentId) {
        final Comment foundComment = this.commentRepository.findById(commentId)
            .orElseThrow(() -> new ResourceNotFoundException("Comment not found for id: " + commentId));

        final Comment updatedComment = this.commentRepository
            .save(new Comment(
                foundComment.getProduct(),
                foundComment.getId(),
                commentDetails.getDescription(),
                commentDetails.getRating())
            );

        return this.modelMapper.map(updatedComment, CommentDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        this.commentRepository.deleteById(id);
    }
}
