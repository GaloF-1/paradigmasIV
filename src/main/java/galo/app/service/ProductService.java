package galo.app.service;
import galo.app.domain.*; import galo.app.repo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service @Transactional
public class ProductService {
    private final ProductRepository productRepo; private final CategoryRepository categoryRepo;
    public ProductService(ProductRepository p, CategoryRepository c){ this.productRepo=p; this.categoryRepo=c; }
    public List<Product> findAll(){ return productRepo.findAll(); }
    public Optional<Product> findById(Long id){ return productRepo.findById(id); }
    public Product create(Product p){
        if(p.getCategory()!=null && p.getCategory().getId()!=null){
            p.setCategory(categoryRepo.findById(p.getCategory().getId()).orElseThrow());
        }
        return productRepo.save(p);
    }
    public Product update(Long id, Product u){
        return productRepo.findById(id).map(p->{
            p.setName(u.getName()); p.setPrice(u.getPrice()); p.setStock(u.getStock());
            p.setImageUrl(u.getImageUrl()); p.setDescription(u.getDescription());
            if(u.getCategory()!=null) p.setCategory(u.getCategory()); return productRepo.save(p);
        }).orElseThrow();
    }
    public void delete(Long id){ productRepo.deleteById(id); }
}
