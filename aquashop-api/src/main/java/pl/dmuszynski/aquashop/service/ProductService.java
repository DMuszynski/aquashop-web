package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.payload.dto.ProductDTO;
import pl.dmuszynski.aquashop.model.Product;

import java.util.List;

public interface ProductService {
    ProductDTO addProduct(ProductDTO productDetails);
    ProductDTO updateProduct(ProductDTO productDetails, Long id);
    ProductDTO findProductDtoById(Long id);
    Product findProductById(Long id);
    List<ProductDTO> findAll();
    void deleteById(Long id);
}
