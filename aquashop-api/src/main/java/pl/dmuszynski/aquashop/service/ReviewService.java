package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.payload.ReviewDTO;

public interface ReviewService {
    ReviewDTO addProductReview(ReviewDTO reviewDetails, Long productId);
    ReviewDTO updateReview(ReviewDTO reviewDetails, Long reviewId);
    void deleteById(Long id);
}
