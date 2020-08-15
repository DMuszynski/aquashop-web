package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.dmuszynski.aquashop.repository.ProductRepository;
import pl.dmuszynski.aquashop.service.ProductService;
import pl.dmuszynski.aquashop.model.Product;

import java.util.List;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product addProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product productDetails, Long id) {
        final Product product = this.findById(id);
        return productRepository
            .save(new Product(product.getId(),
                productDetails.getName(), productDetails.getPrize()));
    }

    @Override
    public Product findById(Long id) throws ResourceNotFoundException {
        return this.productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: " + id));
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
