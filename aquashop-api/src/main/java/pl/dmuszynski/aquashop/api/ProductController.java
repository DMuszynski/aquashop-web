package pl.dmuszynski.aquashop.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import pl.dmuszynski.aquashop.service.ProductService;
import pl.dmuszynski.aquashop.model.Product;

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
    public ResponseEntity<Product> addProduct(@RequestBody @Valid Product product) {
        final Product createdProduct = this.productService.addProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize(value = "hasRole('MODERATOR')")
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid Product product, @PathVariable Long id) {
        final Product updatedProduct = this.productService.updateProduct(product, id);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        final Product foundProduct = this.productService.findById(id);
        return new ResponseEntity<>(foundProduct, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        final List<Product> productList = this.productService.findAll();

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
