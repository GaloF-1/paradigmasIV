package galo.app.web;
import galo.app.domain.Cart; import galo.app.domain.Order; import galo.app.dto.*; import galo.app.service.CartService;
import javax.validation.Valid; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/carts") @CrossOrigin
public class CartController {
    private final CartService svc; public CartController(CartService s){ this.svc=s; }
    @PostMapping public Cart create(){ return svc.createCart(); }
    @GetMapping("/{id}") public Cart get(@PathVariable String id){ return svc.getOrThrow(id); }
    @PostMapping("/{id}/items") public Cart add(@PathVariable String id,@Valid @RequestBody AddCartItemRequest r){ return svc.addItem(id,r.productId(),r.quantity()); }
    @PatchMapping("/{id}/items") public Cart upd(@PathVariable String id,@Valid @RequestBody UpdateCartItemRequest r){ return svc.updateItem(id,r.cartItemId(),r.quantity()); }
    @DeleteMapping("/{id}/items/{itemId}") public Cart del(@PathVariable String id,@PathVariable Long itemId){ return svc.removeItem(id,itemId); }
    @PostMapping("/{id}/checkout") public Order checkout(@PathVariable String id,@Valid @RequestBody CheckoutRequest r){ return svc.checkout(id,r); }
}
