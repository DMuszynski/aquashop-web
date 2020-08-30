package pl.dmuszynski.aquashop.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import pl.dmuszynski.aquashop.payload.PromotionDTO;
import pl.dmuszynski.aquashop.service.PromotionService;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize(value = "hasRole('MODERATOR')")
@RequestMapping(value = "/product-management/products/{productId}/promotion-management/promotions")
public class PromotionController {

    private final PromotionService promotionService;

    @PostMapping
    public ResponseEntity<PromotionDTO> addProductPromotion(@RequestBody @Valid PromotionDTO promotionDetails, @PathVariable Long productId) {
        final PromotionDTO createdPromotionDto = this.promotionService.addPromotion(promotionDetails, productId);
        return new ResponseEntity<>(createdPromotionDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PromotionDTO> updatePromotion(@RequestBody @Valid PromotionDTO promotionDetails, @PathVariable Long id) {
        final PromotionDTO updatedPromotionDto = this.promotionService.updatePromotion(promotionDetails, id);
        return new ResponseEntity<>(updatedPromotionDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PromotionDTO> findPromotionDtoById(@PathVariable Long id) {
        final PromotionDTO foundPromotionDto = this.promotionService.findPromotionDtoById(id);
        return new ResponseEntity<>(foundPromotionDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PromotionDTO>> findAllPromotionDTO() {
        final List<PromotionDTO> foundPromotionDtoList = this.promotionService.findAllPromotionDTO();

        if (!foundPromotionDtoList.isEmpty())
            return new ResponseEntity<>(foundPromotionDtoList, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        this.promotionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
