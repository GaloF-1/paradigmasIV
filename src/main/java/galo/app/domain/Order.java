package galo.app.domain;
import javax.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

@Entity @Table(name="orders")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Order {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false) private String buyerEmail;
    private String buyerName; private String shippingAddress;
    private OffsetDateTime createdAt;
    @Enumerated(EnumType.STRING) private Status status;
    @OneToMany(mappedBy="order", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
    private List<OrderItem> items = new ArrayList<>();
    @Column(nullable=false) private BigDecimal total;
    public enum Status { NEW, PAID, SHIPPED, CANCELLED }
}
