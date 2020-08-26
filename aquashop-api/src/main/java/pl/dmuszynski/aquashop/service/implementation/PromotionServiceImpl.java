package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import pl.dmuszynski.aquashop.model.Product;
import pl.dmuszynski.aquashop.repository.PromotionRepository;
import pl.dmuszynski.aquashop.service.ProductService;
import pl.dmuszynski.aquashop.service.PromotionService;
import pl.dmuszynski.aquashop.payload.PromotionDTO;
import pl.dmuszynski.aquashop.model.Promotion;

import java.util.stream.Collectors;
import java.util.List;
import java.time.LocalDate;

@Service(value = "promotionService")
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public PromotionServiceImpl(PromotionRepository promotionRepository, ProductService productService,
                                ModelMapper modelMapper)
    {
        this.promotionRepository = promotionRepository;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @Override
    public PromotionDTO addPromotion(PromotionDTO promotionDetails, Long productId) {
        final Product foundProduct = this.productService.findProductById(productId);
        final Promotion savedPromotion = this.promotionRepository
            .save(new Promotion(
                foundProduct,
                promotionDetails.getPercentValue(),
                promotionDetails.getStartDate(),
                promotionDetails.getEndDate())
            );

        return this.modelMapper.map(savedPromotion, PromotionDTO.class);
    }

    @Override
    public PromotionDTO updatePromotion(PromotionDTO promotionDetails, Long promotionId) {
        final Promotion foundPromotion = this.findPromotionById(promotionId);
        final Promotion updatedPromotion = this.promotionRepository
            .save(new Promotion(
                foundPromotion.getId(),
                foundPromotion.getProduct(),
                promotionDetails.getPercentValue(),
                promotionDetails.getStartDate(),
                promotionDetails.getEndDate())
            );

        return this.modelMapper.map(updatedPromotion, PromotionDTO.class);
    }

    @Override
    public PromotionDTO findPromotionDtoById(Long id) {
        final Promotion foundPromotion = this.findPromotionById(id);
        return this.modelMapper.map(foundPromotion, PromotionDTO.class);
    }

    @Override
    public List<PromotionDTO> findAllPromotionDTO() {
        final List<Promotion> foundPromotionList = promotionRepository.findAll();
        return foundPromotionList.stream()
            .map(promotion -> this.modelMapper.map(promotion, PromotionDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public Promotion findPromotionById(Long id) throws ResourceNotFoundException {
        return this.promotionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Promotion not found for this id: " + id));
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "GMT+2")
    public void deleteCompletedPromotions() {
        final List<Promotion> completedPromotions = this.promotionRepository.findAll().stream()
            .filter(promotion -> promotion.getEndDate().isAfter(LocalDate.now()))
            .collect(Collectors.toList());

        this.promotionRepository.deleteAll(completedPromotions);
    }

    @Override
    public void deleteById(Long id) {
        this.promotionRepository.deleteById(id);
    }
}