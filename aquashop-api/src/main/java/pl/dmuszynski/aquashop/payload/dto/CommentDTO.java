package pl.dmuszynski.aquashop.payload.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CommentDTO implements Serializable {
    private Long id;
    private String description;
    private int rating;
}
