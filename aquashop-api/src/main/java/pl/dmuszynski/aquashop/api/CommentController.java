package pl.dmuszynski.aquashop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.dmuszynski.aquashop.model.Comment;
import pl.dmuszynski.aquashop.service.CommentService;

@RestController
@RequestMapping(value = "/product-management/products/{id}/comment-management/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    void addProductComment(@RequestBody Comment comment, @PathVariable(value = "id") Long productId) {
        this.commentService.addProductComment(comment, productId);
    }

    @PatchMapping(value = "/{id}/description")
    void updateDescriptionById(@RequestBody Comment comment, @PathVariable Long id) {
        this.commentService.updateDescriptionById(comment.getDescription(), id);
    }

    @PatchMapping(value = "/{id}/mark")
    void updateMarkById(@RequestBody Comment comment, @PathVariable Long id) {
        this.commentService.updateMarkById(comment.getMark(), id);
    }

    @DeleteMapping(value = "/{id}")
    void deleteById(@PathVariable Long id) {
        this.commentService.deleteById(id);
    }
}
