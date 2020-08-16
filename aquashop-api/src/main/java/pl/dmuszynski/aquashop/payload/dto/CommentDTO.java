package pl.dmuszynski.aquashop.payload.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentDTO implements Serializable {
    private Long id;
    private String description;
    private int rating;
}
