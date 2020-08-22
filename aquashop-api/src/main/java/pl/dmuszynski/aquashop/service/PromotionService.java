package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.payload.PromotionDTO;

import java.util.List;

public interface PromotionService {
    PromotionDTO addPromotion(PromotionDTO promotionDetails);
    PromotionDTO updatePromotion(PromotionDTO promotionDetails, Long id);
    List<PromotionDTO> findAll();
    void deleteById(Long id);
}
