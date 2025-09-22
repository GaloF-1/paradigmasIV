package galo.app.web;
import galo.app.domain.Order; import galo.app.service.OrderService;
import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/orders") @CrossOrigin
public class OrderController {
    private final OrderService svc; public OrderController(OrderService s){ this.svc=s; }
    @GetMapping("/{id}") public ResponseEntity<Order> get(@PathVariable Long id){
        return svc.get(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}