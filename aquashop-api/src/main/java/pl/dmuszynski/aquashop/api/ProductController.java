package pl.dmuszynski.aquashop.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import pl.dmuszynski.aquashop.service.ProductService;
import pl.dmuszynski.aquashop.payload.dto.ProductDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/product-management/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @PreAuthorize(value = "hasRole('MODERATOR')")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody @Valid ProductDTO productDetails) {
        final ProductDTO createdProduct = this.productService.addProduct(productDetails);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize(value = "hasRole('MODERATOR')")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody @Valid ProductDTO productDetails, @PathVariable Long id) {
        final ProductDTO updatedProduct = this.productService.updateProduct(productDetails, id);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findProductDtoById(@PathVariable Long id) {
        final ProductDTO foundProduct = this.productService.findProductDtoById(id);
        return new ResponseEntity<>(foundProduct, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        final List<ProductDTO> productList = this.productService.findAll();

        if (!productList.isEmpty())
            return new ResponseEntity<>(productList, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize(value = "hasRole('MODERATOR')")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        this.productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
