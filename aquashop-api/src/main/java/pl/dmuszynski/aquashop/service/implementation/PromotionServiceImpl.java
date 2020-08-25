package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import pl.dmuszynski.aquashop.repository.PromotionRepository;
import pl.dmuszynski.aquashop.service.PromotionService;
import pl.dmuszynski.aquashop.payload.PromotionDTO;
import pl.dmuszynski.aquashop.model.Promotion;

import java.util.stream.Collectors;
import java.util.List;

@Service(value = "promotionService")
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PromotionServiceImpl(PromotionRepository promotionRepository, ModelMapper modelMapper) {
        this.promotionRepository = promotionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PromotionDTO addPromotion(PromotionDTO promotionDetails) {
        final Promotion savedPromotion = this.promotionRepository
            .save(new Promotion(
                promotionDetails.getName(),
                promotionDetails.getPercentValue(),
                promotionDetails.getCreationDate(),
                promotionDetails.getEndDate())
            );

        return this.modelMapper.map(savedPromotion, PromotionDTO.class);
    }

    @Override
    public PromotionDTO updatePromotion(PromotionDTO promotionDetails, Long id) {
        final Promotion foundPromotion = this.findPromotionById(id);
        final Promotion updatedPromotion = this.promotionRepository
            .save(new Promotion(
                foundPromotion.getId(),
                promotionDetails.getName(),
                promotionDetails.getPercentValue(),
                foundPromotion.getCreationDate(),
                foundPromotion.getEndDate())
            );

        return this.modelMapper.map(updatedPromotion, PromotionDTO.class);
    }

    @Override
    public PromotionDTO findPromotionDtoById(Long id) {
        final Promotion foundPromotion = this.findPromotionById(id);
        return this.modelMapper.map(foundPromotion, PromotionDTO.class);
    }

    @Override
    public Promotion findPromotionById(Long id) throws ResourceNotFoundException {
        return this.promotionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Promotion not found for this id: " + id));
    }

    @Override
    public List<PromotionDTO> findAll() {
        return promotionRepository.findAll()
            .stream()
            .map(promotion -> this.modelMapper.map(promotion, PromotionDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        this.promotionRepository.deleteById(id);
    }
}
