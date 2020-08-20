package pl.dmuszynski.aquashop.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import pl.dmuszynski.aquashop.payload.CommentDTO;
import pl.dmuszynski.aquashop.service.CommentService;

import javax.validation.Valid;

@RestController
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping(value = "/product-management/products/{productId}/comment-management/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentDTO> addProductComment(@RequestBody @Valid CommentDTO commentDetails, @PathVariable Long productId) {
        final CommentDTO createdComment = this.commentService.addProductComment(commentDetails, productId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CommentDTO> updateComment(@RequestBody @Valid CommentDTO commentDetails, @PathVariable Long id) {
        final CommentDTO updatedComment = this.commentService.updateComment(commentDetails, id);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        this.commentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
