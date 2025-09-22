package galo.app.domain;

import javax.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false) private String name;
    @ManyToOne(optional=false) private Category category;
    @Column(nullable=false) private BigDecimal price;
    @Column(nullable=false) private Integer stock;
    private String imageUrl;
    @Column(length=2000) private String description;
}
