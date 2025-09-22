package galo.app.domain;
import javax.persistence.*; import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CartItem {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false) private Product product;
    @ManyToOne(optional=false) private Cart cart;
    @Column(nullable=false) private Integer quantity;
}
