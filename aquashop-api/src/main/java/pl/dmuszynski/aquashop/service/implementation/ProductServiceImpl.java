package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dmuszynski.aquashop.exception.ProductNotFoundException;
import pl.dmuszynski.aquashop.repository.ProductRepository;
import pl.dmuszynski.aquashop.service.ProductService;
import pl.dmuszynski.aquashop.model.Product;

import javax.transaction.Transactional;

@Service @Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public void updatePrizeById(float prize, Long id) {
        this.productRepository.updatePrizeById(prize, id);
    }

    @Override
    public void updateNameById(String name, Long id) {
        this.productRepository.updateNameById(name, id);
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public Product findById(Long id) {
        return this.productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
