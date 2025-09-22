package galo.app.domain;

import javax.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;
import java.util.*;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Cart {
    @Id private String id;                       // UUID String
    private OffsetDateTime createdAt;
    @OneToMany(mappedBy="cart", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
    private List<CartItem> items = new ArrayList<>();
}
