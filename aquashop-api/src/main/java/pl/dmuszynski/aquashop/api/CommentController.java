package pl.dmuszynski.aquashop.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import pl.dmuszynski.aquashop.service.CommentService;
import pl.dmuszynski.aquashop.model.Comment;

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
    public ResponseEntity<Comment> addProductComment(@RequestBody Comment comment, @PathVariable Long productId) {
        final Comment createdComment = this.commentService.addProductComment(comment, productId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment, @PathVariable Long id) {
        final Comment updatedComment = this.commentService.updateComment(comment, id);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Comment> findById(@PathVariable Long id) {
        final Comment foundComment = this.commentService.findById(id);
        return new ResponseEntity<>(foundComment, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        this.commentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
