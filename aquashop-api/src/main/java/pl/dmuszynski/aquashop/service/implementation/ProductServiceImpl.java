package pl.dmuszynski.aquashop.service.implementation;

import pl.dmuszynski.aquashop.exception.ProductNotFoundException;
import pl.dmuszynski.aquashop.repository.ProductRepository;
import pl.dmuszynski.aquashop.service.ProductService;
import pl.dmuszynski.aquashop.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product updatePrizeById(float prize, Long id) {
        final Product product = this.productRepository.findById(id)
            .orElseThrow(ProductNotFoundException::new);
        product.setPrize(prize);

        this.productRepository.updatePrizeById(prize, id);
        return product;
    }

    @Override
    public Product updateNameById(String name, Long id) {
        final Product product = this.productRepository.findById(id)
            .orElseThrow(ProductNotFoundException::new);
        product.setName(name);

        this.productRepository.updateNameById(name, id);
        return product;
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
