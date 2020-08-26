package pl.dmuszynski.aquashop.payload;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class PromotionDTO implements Serializable {
    private Long id;
    private int percentValue;
    private LocalDate startDate;
    private LocalDate endDate;
}