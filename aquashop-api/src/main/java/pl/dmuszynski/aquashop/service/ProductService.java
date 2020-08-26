package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.payload.ProductDTO;
import pl.dmuszynski.aquashop.model.Product;

import java.util.List;

public interface ProductService {
    ProductDTO addProduct(ProductDTO productDetails);
    ProductDTO updateProduct(ProductDTO productDetails, Long id);
    ProductDTO findProductDtoById(Long id);
    List<ProductDTO> findAllProductDto();
    Product findProductById(Long id);
    void deleteById(Long id);
}
