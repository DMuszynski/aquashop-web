package pl.dmuszynski.aquashop.payload;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;

@Data
public class PromotionDTO implements Serializable {
    private Long id;
    private String name;
    private int percentValue;
    private LocalDateTime createdDate;
    private LocalDateTime endDate;
}
