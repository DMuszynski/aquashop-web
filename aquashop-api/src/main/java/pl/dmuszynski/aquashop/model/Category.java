package pl.dmuszynski.aquashop.model;

import javax.validation.constraints.NotBlank;
import javax.persistence.*;

import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data @NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @NotBlank
    private String name;
}
