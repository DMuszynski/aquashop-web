package pl.dmuszynski.aquashop.payload;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import pl.dmuszynski.aquashop.model.Review;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {
    private Long id;
    private String name;
    private float price;
//    private List<Review> reviews;
}
