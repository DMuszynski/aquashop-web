package pl.dmuszynski.aquashop.payload.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private float prize;
    private List<CommentDTO> comments;
}
