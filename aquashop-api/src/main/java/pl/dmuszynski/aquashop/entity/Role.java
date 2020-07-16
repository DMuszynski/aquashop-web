package pl.dmuszynski.aquashop.entity;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", unique = true, nullable = false)
    private Long Id;

    @Column(length = 30 , unique = true, nullable = false)
    private String name;

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
