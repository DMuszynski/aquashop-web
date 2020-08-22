package pl.dmuszynski.aquashop.service.implementation;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import pl.dmuszynski.aquashop.repository.PromotionRepository;
import pl.dmuszynski.aquashop.service.PromotionService;
import pl.dmuszynski.aquashop.service.ProductService;
import pl.dmuszynski.aquashop.payload.PromotionDTO;
import pl.dmuszynski.aquashop.model.Promotion;
import pl.dmuszynski.aquashop.model.Product;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;

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
    public PromotionDTO addPromotion(PromotionDTO promotionDetails) {
        final Promotion savedPromotion = this.promotionRepository
            .save(new Promotion(
                promotionDetails.getPercentValue(),
                promotionDetails.getProducts().stream().map(productDTO -> this.modelMapper.map(productDTO, Product.class)).collect(Collectors.toList()),
                promotionDetails.getCreatedDate(),
                promotionDetails.getEndDate())
            );

        return this.modelMapper.map(savedPromotion, PromotionDTO.class);
    }

    @Override
    public PromotionDTO updatePromotion(PromotionDTO promotionDetails, Long id) throws ResourceNotFoundException {
        final Promotion foundPromotion = this.promotionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id" + id));

        final Promotion updatedPromotion = this.promotionRepository
            .save(new Promotion(
                foundPromotion.getId(),
                promotionDetails.getPercentValue(),
                promotionDetails.getProducts().stream().map(productDTO -> this.modelMapper.map(productDTO, Product.class)).collect(Collectors.toList()),
                promotionDetails.getCreatedDate(),
                promotionDetails.getEndDate())
            );

        return this.modelMapper.map(updatedPromotion, PromotionDTO.class);
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
        this.productService.deleteById(id);
    }
}
