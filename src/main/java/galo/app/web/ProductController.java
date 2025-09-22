package galo.app.web;
import galo.app.domain.Product; import galo.app.service.ProductService;
import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/products") @CrossOrigin
public class ProductController {
    private final ProductService svc; public ProductController(ProductService s){ this.svc=s; }
    @GetMapping public List<Product> all(){ return svc.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Product> one(@PathVariable Long id){
        return svc.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public Product create(@RequestBody Product p){ return svc.create(p); }
    @PutMapping("/{id}") public ResponseEntity<Product> upd(@PathVariable Long id,@RequestBody Product p){ return ResponseEntity.ok(svc.update(id,p)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> del(@PathVariable Long id){ svc.delete(id); return ResponseEntity.noContent().build(); }
}
