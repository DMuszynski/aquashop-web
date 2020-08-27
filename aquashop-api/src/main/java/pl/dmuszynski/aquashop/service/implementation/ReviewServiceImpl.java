package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import pl.dmuszynski.aquashop.repository.ReviewRepository;
import pl.dmuszynski.aquashop.service.ProductService;
import pl.dmuszynski.aquashop.service.ReviewService;
import pl.dmuszynski.aquashop.payload.ReviewDTO;
import pl.dmuszynski.aquashop.model.Product;
import pl.dmuszynski.aquashop.model.Review;

import lombok.RequiredArgsConstructor;

@Service(value = "reviewService")
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductService productService, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReviewDTO addProductReview(ReviewDTO reviewDetails, Long productId) {
        final Product foundProduct = this.productService.findProductById(productId);
        final Review savedReview = this.reviewRepository
            .save(new Review(
                foundProduct,
                reviewDetails.getGrade(),
                reviewDetails.getReviewComment())
            );

        return this.modelMapper.map(savedReview, ReviewDTO.class);
    }

    @Override
    public ReviewDTO updateReview(ReviewDTO reviewDetails, Long reviewId) {
        final Review foundReview = this.reviewRepository.findById(reviewId)
            .orElseThrow(() -> new ResourceNotFoundException("Review not found for id: " + reviewId));

        final Review updatedReview = this.reviewRepository
            .save(new Review(
                foundReview.getId(),
                foundReview.getProduct(),
                foundReview.getGrade(),
                foundReview.getReviewComment())
            );

        return this.modelMapper.map(updatedReview, ReviewDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        this.reviewRepository.deleteById(id);
    }
}
