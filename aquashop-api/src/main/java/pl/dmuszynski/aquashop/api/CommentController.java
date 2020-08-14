package pl.dmuszynski.aquashop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.dmuszynski.aquashop.model.Address;
import pl.dmuszynski.aquashop.model.Comment;
import pl.dmuszynski.aquashop.service.CommentService;

@RestController
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping(value = "/product-management/products/{id}/comment-management/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> addProductComment(@RequestBody Comment comment, @PathVariable(value = "id") Long productId) {
        final Comment createdComment = this.commentService.addProductComment(comment, productId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}/description")
    public ResponseEntity<Comment> updateDescriptionById(@RequestBody String description, @PathVariable Long id) {
        final Comment updateComment = this.commentService.updateDescriptionById(description, id);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}/mark")
    public ResponseEntity<Comment> updateMarkById(@RequestBody int mark, @PathVariable Long id) {
        final Comment updateComment = this.commentService.updateMarkById(mark, id);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        this.commentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
