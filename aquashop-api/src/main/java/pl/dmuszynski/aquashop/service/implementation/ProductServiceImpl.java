package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import pl.dmuszynski.aquashop.repository.ProductRepository;
import pl.dmuszynski.aquashop.service.PromotionService;
import pl.dmuszynski.aquashop.service.ProductService;
import pl.dmuszynski.aquashop.payload.ProductDTO;
import pl.dmuszynski.aquashop.model.Product;
import pl.dmuszynski.aquashop.model.Promotion;

import java.util.stream.Collectors;
import java.util.Optional;
import java.util.List;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final PromotionService promotionService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, PromotionService promotionService,
                              ModelMapper modelMapper)
    {
        this.productRepository = productRepository;
        this.promotionService = promotionService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDetails) {
        final Promotion foundPromotion = Optional.ofNullable(productDetails.getPromotion())
            .map(promotion -> this.promotionService.findPromotionById(promotion.getId()))
            .orElse(null);

        final Product createdProduct = this.productRepository
            .save(new Product(
                foundPromotion,
                productDetails.getName(),
                productDetails.getPrize())
            );

        return this.modelMapper.map(createdProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDetails, Long productId) {
        final Product foundProduct = this.findProductById(productId);

        final Promotion foundPromotion = Optional.ofNullable(productDetails.getPromotion())
            .map(promotion -> this.promotionService.findPromotionById(promotion.getId()))
            .orElse(null);

        final Product updatedProduct = this.productRepository
            .save(new Product(
                foundPromotion,
                foundProduct.getId(),
                productDetails.getName(),
                productDetails.getPrize(),
                foundProduct.getComments())
            );

        return this.modelMapper.map(updatedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO findProductDtoById(Long id) {
        final Product foundProduct = this.findProductById(id);
        return this.modelMapper.map(foundProduct, ProductDTO.class);
    }

    @Override
    public Product findProductById(Long id) throws ResourceNotFoundException {
        return this.productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: " + id));
    }

    @Override
    public List<ProductDTO> findAll() {
        final List<Product> productList = this.productRepository.findAll();
        return productList.stream()
            .map(product -> this.modelMapper.map(product, ProductDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
