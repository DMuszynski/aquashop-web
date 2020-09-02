package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import pl.dmuszynski.aquashop.repository.ReviewRepository;
import pl.dmuszynski.aquashop.service.ProductService;
import pl.dmuszynski.aquashop.service.ReviewService;
import pl.dmuszynski.aquashop.payload.ReviewDTO;
import pl.dmuszynski.aquashop.model.Product;
import pl.dmuszynski.aquashop.model.Review;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service(value = "reviewService")
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Override
    public ReviewDTO addProductReview(ReviewDTO reviewDetails, Long productId) {
        final Product foundProduct = this.productService.findProductById(productId);
        final Review savedReview = this.reviewRepository
            .save(Review.builder()
                .product(foundProduct)
                .grade(reviewDetails.getGrade())
                .reviewComment(reviewDetails.getReviewComment())
                .build()
            );

        return this.modelMapper.map(savedReview, ReviewDTO.class);
    }

    @Override
    public ReviewDTO updateReview(ReviewDTO reviewDetails, Long reviewId) {
        final Review foundReview = this.reviewRepository.findById(reviewId)
            .orElseThrow(() -> new ResourceNotFoundException("Review not found for id: " + reviewId));

        final Review updatedReview = this.reviewRepository
            .save(Review.builder()
                .id(foundReview.getId())
                .product(foundReview.getProduct())
                .grade(reviewDetails.getGrade())
                .reviewComment(reviewDetails.getReviewComment())
                .build()
            );

        return this.modelMapper.map(updatedReview, ReviewDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        this.reviewRepository.deleteById(id);
    }
}
