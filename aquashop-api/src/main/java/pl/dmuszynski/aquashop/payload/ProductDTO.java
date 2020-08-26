package pl.dmuszynski.aquashop.payload;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ProductDTO implements Serializable {
    private Long id;
    private String name;
    private float prize;
    private List<CommentDTO> comments;
}
