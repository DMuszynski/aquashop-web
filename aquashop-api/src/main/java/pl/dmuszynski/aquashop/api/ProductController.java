package pl.dmuszynski.aquashop.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import pl.dmuszynski.aquashop.service.ProductService;
import pl.dmuszynski.aquashop.payload.ProductDTO;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/product-management/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize(value = "hasRole('MODERATOR')")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody @Valid ProductDTO productDetails) {
        final ProductDTO createdProductDto = this.productService.addProduct(productDetails);
        return new ResponseEntity<>(createdProductDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize(value = "hasRole('MODERATOR')")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody @Valid ProductDTO productDetails, @PathVariable Long id) {
        final ProductDTO updatedProductDto = this.productService.updateProduct(productDetails, id);
        return new ResponseEntity<>(updatedProductDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findProductDtoById(@PathVariable Long id) {
        final ProductDTO foundProductDto = this.productService.findProductDtoById(id);
        return new ResponseEntity<>(foundProductDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAllProductDto() {
        final List<ProductDTO> foundProductDtoList = this.productService.findAllProductDto();

        if (!foundProductDtoList.isEmpty())
            return new ResponseEntity<>(foundProductDtoList, HttpStatus.OK);
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
