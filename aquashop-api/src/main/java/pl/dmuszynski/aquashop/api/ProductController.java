package pl.dmuszynski.aquashop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.dmuszynski.aquashop.model.Product;
import pl.dmuszynski.aquashop.service.ProductService;

@RestController
@RequestMapping(value = "/product-management/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        this.productService.addProduct(product);
    }

    @PatchMapping(value = "/{id}/prize")
    public void updatePrizeById(@RequestBody Product product, @PathVariable Long id) {
        this.productService.updatePrizeById(product.getPrize(), id);
    }

    @PatchMapping(value = "/{id}/name")
    public void updateNameById(@RequestBody Product product, @PathVariable Long id) {
        this.productService.updateNameById(product.getName(), id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        this.productService.deleteById(id);
    }

}
