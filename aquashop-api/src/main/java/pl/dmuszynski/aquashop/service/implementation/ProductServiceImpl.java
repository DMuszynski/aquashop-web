package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import pl.dmuszynski.aquashop.repository.ProductRepository;
import pl.dmuszynski.aquashop.service.ProductService;
import pl.dmuszynski.aquashop.payload.ProductDTO;
import pl.dmuszynski.aquashop.model.Product;

import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;
import java.util.List;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDetails) {
        final Product createdProduct = this.productRepository
            .save(new Product(productDetails.getName(), productDetails.getPrice()));

        return this.modelMapper.map(createdProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDetails, Long productId) {
        final Product foundProduct = this.findProductById(productId);
        final Product updatedProduct = this.productRepository
            .save(new Product(
                foundProduct.getId(),
                productDetails.getName(),
                productDetails.getPrice(),
                foundProduct.getReviews())
            );

        return this.modelMapper.map(updatedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO findProductDtoById(Long id) {
        final Product foundProduct = this.findProductById(id);
        return this.modelMapper.map(foundProduct, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> findAllProductDto() {
        final List<Product> foundProductList = this.productRepository.findAll();
        return foundProductList.stream()
            .map(product -> this.modelMapper.map(product, ProductDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public Product findProductById(Long id) throws ResourceNotFoundException {
        return this.productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
