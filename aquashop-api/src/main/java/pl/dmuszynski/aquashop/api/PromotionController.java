package pl.dmuszynski.aquashop.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import pl.dmuszynski.aquashop.payload.PromotionDTO;
import pl.dmuszynski.aquashop.service.PromotionService;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize(value = "hasRole('MODERATOR')")
@RequestMapping(value = "/promotion-management/promotions")
public class PromotionController {

    private final PromotionService promotionService;

    @Autowired
    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @PostMapping
    public ResponseEntity<PromotionDTO> addPromotion(@RequestBody @Valid PromotionDTO promotionDetails) {
        final PromotionDTO createdPromotion = this.promotionService.addPromotion(promotionDetails);
        return new ResponseEntity<>(createdPromotion, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PromotionDTO> updatePromotion(@RequestBody @Valid PromotionDTO promotionDetails, @PathVariable Long id) {
        final PromotionDTO updatedPromotion = this.promotionService.updatePromotion(promotionDetails, id);
        return new ResponseEntity<>(updatedPromotion, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PromotionDTO> findById(@PathVariable Long id) {
        final PromotionDTO foundPromotion = this.promotionService.findPromotionDtoById(id);
        return new ResponseEntity<>(foundPromotion, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PromotionDTO>> findAllPromotionDTO() {
        final List<PromotionDTO> foundPromotionsList = this.promotionService.findAll();

        if (!foundPromotionsList.isEmpty())
            return new ResponseEntity<>(foundPromotionsList, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        this.promotionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
