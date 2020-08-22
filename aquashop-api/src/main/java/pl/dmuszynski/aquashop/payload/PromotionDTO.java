package pl.dmuszynski.aquashop.payload;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PromotionDTO implements Serializable {
    private Long id;
    private int percentValue;
    private List<ProductDTO> products;
    private LocalDateTime createdDate;
    private LocalDateTime endDate;
}
