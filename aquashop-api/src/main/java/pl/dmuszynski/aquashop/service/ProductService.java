package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product addProduct(Product product);
    Product updatePrizeById(float prize, Long id);
    Product updateNameById(String name, Long id);
    void deleteById(Long id);
}
