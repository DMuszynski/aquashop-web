package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import pl.dmuszynski.aquashop.model.Review;
import pl.dmuszynski.aquashop.repository.ProductRepository;
import pl.dmuszynski.aquashop.service.ProductService;
import pl.dmuszynski.aquashop.payload.ProductDTO;
import pl.dmuszynski.aquashop.model.Product;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;
import java.util.List;

@RequiredArgsConstructor
@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProductDTO addProduct(ProductDTO productDetails) {
        final Product createdProduct = this.productRepository
            .save(Product.builder()
                .name(productDetails.getName())
                .price(productDetails.getPrice())
                .build()
            );

        return this.modelMapper.map(createdProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDetails, Long productId) {
        final Product foundProduct = this.findProductById(productId);
        final Product updatedProduct = this.productRepository
            .save(Product.builder()
                .id(foundProduct.getId())
                .name(productDetails.getName())
                .price(productDetails.getPrice())
                .build()
            );

        return this.modelMapper.map(updatedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO findProductDtoById(Long id) {
        return this.productRepository.findProductDtoById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: " + id));
    }

    @Override
    public Product findProductById(Long id) throws ResourceNotFoundException {
        return this.productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: " + id));
    }

    @Override
    public List<ProductDTO> findAllProductDto() {
        return this.productRepository.findAllProductDto();
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
