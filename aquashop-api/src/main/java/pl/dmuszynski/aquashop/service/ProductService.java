package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    Product updateProduct(Product product, Long id);
    Product findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
}
