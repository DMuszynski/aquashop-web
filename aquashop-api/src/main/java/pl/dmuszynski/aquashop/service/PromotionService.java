package pl.dmuszynski.aquashop.service;

import pl.dmuszynski.aquashop.model.Promotion;
import pl.dmuszynski.aquashop.payload.PromotionDTO;

import java.util.List;

public interface PromotionService {
    PromotionDTO addPromotion(PromotionDTO promotionDetails);
    PromotionDTO updatePromotion(PromotionDTO promotionDetails, Long id);
    PromotionDTO findPromotionDtoById(Long id);
    Promotion findPromotionById(Long id);
    List<PromotionDTO> findAll();
    void deleteById(Long id);
}
