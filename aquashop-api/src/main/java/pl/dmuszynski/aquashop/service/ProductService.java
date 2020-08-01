package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.Product;

public interface ProductService {
    void addProduct(Product product);
    void updatePrizeById(float prize, Long id);
    void updateNameById(String name, Long id);
    void deleteById(Long id);
    Product findById(Long id);
}
