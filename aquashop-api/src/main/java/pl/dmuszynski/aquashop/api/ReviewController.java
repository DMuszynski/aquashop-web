package pl.dmuszynski.aquashop.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import pl.dmuszynski.aquashop.payload.ReviewDTO;
import pl.dmuszynski.aquashop.service.ReviewService;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping(value = "/product-management/products/{productId}/comment-management/comments")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDTO> addProductComment(@RequestBody @Valid ReviewDTO reviewDetails, @PathVariable Long productId) {
        final ReviewDTO createdReviewDto = this.reviewService.addProductReview(reviewDetails, productId);
        return new ResponseEntity<>(createdReviewDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ReviewDTO> updateComment(@RequestBody @Valid ReviewDTO reviewDetails, @PathVariable Long id) {
        final ReviewDTO updatedReviewDto = this.reviewService.updateReview(reviewDetails, id);
        return new ResponseEntity<>(updatedReviewDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        this.reviewService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
