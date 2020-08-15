package pl.dmuszynski.aquashop.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import pl.dmuszynski.aquashop.service.ProductService;
import pl.dmuszynski.aquashop.model.Product;

import java.util.List;

@RestController
@RequestMapping(value = "/product-management/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        final List<Product> productList = this.productService.findAll();

        if (!productList.isEmpty())
            return new ResponseEntity<>(productList, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    @PreAuthorize(value = "hasAnyRole('MODERATOR','ADMIN')")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        final Product createdProduct = this.productService.addProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}/prize")
    @PreAuthorize(value = "hasAnyRole('MODERATOR','ADMIN')")
    public ResponseEntity<Product> updatePrizeById(@RequestBody float prize, @PathVariable Long id) {
        final Product updatedProduct = this.productService.updatePrizeById(prize, id);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}/name")
    @PreAuthorize(value = "hasAnyRole('MODERATOR','ADMIN')")
    public ResponseEntity<Product> updateNameById(@RequestBody String name, @PathVariable Long id) {
        final Product updatedProduct = this.productService.updateNameById(name, id);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize(value = "hasAnyRole('MODERATOR','ADMIN')")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        this.productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
