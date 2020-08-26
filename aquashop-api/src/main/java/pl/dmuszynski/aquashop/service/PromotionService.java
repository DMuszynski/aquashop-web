package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.Promotion;
import pl.dmuszynski.aquashop.payload.PromotionDTO;

import java.util.List;

public interface PromotionService {
    PromotionDTO addPromotion(PromotionDTO promotionDetails, Long productId);
    PromotionDTO updatePromotion(PromotionDTO promotionDetails, Long id);
    PromotionDTO findPromotionDtoById(Long id);
    List<PromotionDTO> findAllPromotionDTO();
    Promotion findPromotionById(Long id);
    void deleteById(Long id);
}
