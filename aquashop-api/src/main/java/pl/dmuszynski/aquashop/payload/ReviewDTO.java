package pl.dmuszynski.aquashop.payload;

import java.io.Serializable;

import lombok.Data;

@Data
public class ReviewDTO implements Serializable {
    private Long id;
    private Short grade;
    private String reviewComment;
}