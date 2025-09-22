package galo.app.service;
import galo.app.domain.*;
import galo.app.dto.CheckoutRequest;
import galo.app.repo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

@Service @Transactional
public class CartService {
    private final CartRepository cartRepo; private final ProductRepository productRepo; private final OrderRepository orderRepo;
    public CartService(CartRepository c, ProductRepository p, OrderRepository o){ cartRepo=c; productRepo=p; orderRepo=o; }
    public Cart createCart(){ Cart c=new Cart(); c.setId(UUID.randomUUID().toString()); c.setCreatedAt(OffsetDateTime.now()); return cartRepo.save(c); }
    public Cart getOrThrow(String id){ return cartRepo.findById(id).orElseThrow(); }
    public Cart addItem(String cartId, Long productId, int qty){
        Cart cart=getOrThrow(cartId); Product prod=productRepo.findById(productId).orElseThrow();
        cart.getItems().stream().filter(i->i.getProduct().getId().equals(productId)).findFirst()
                .ifPresentOrElse(i->i.setQuantity(i.getQuantity()+qty),
                        ()->cart.getItems().add(CartItem.builder().cart(cart).product(prod).quantity(qty).build()));
        return cartRepo.save(cart);
    }
    public Cart updateItem(String cartId, Long itemId, int qty){
        Cart cart=getOrThrow(cartId);
        cart.getItems().stream().filter(i->i.getId().equals(itemId)).findFirst().orElseThrow().setQuantity(qty);
        return cartRepo.save(cart);
    }
    public Cart removeItem(String cartId, Long itemId){
        Cart cart=getOrThrow(cartId); cart.getItems().removeIf(i->i.getId().equals(itemId)); return cartRepo.save(cart);
    }
    public Order checkout(String cartId, CheckoutRequest req){
        Cart cart=getOrThrow(cartId); if(cart.getItems().isEmpty()) throw new IllegalStateException("Cart is empty");
        for(CartItem ci: cart.getItems()) if(ci.getQuantity()>ci.getProduct().getStock())
            throw new IllegalStateException("Not enough stock for "+ci.getProduct().getName());
        Order order=new Order(); order.setBuyerEmail(req.email()); order.setBuyerName(req.name());
        order.setShippingAddress(req.shippingAddress()); order.setCreatedAt(OffsetDateTime.now()); order.setStatus(Order.Status.NEW);
        BigDecimal total=BigDecimal.ZERO;
        for(CartItem ci: cart.getItems()){
            OrderItem oi=new OrderItem(); oi.setOrder(order); oi.setProduct(ci.getProduct());
            oi.setQuantity(ci.getQuantity()); oi.setUnitPrice(ci.getProduct().getPrice()); order.getItems().add(oi);
            total=total.add(ci.getProduct().getPrice().multiply(BigDecimal.valueOf(ci.getQuantity())));
            ci.getProduct().setStock(ci.getProduct().getStock()-ci.getQuantity());
        }
        order.setTotal(total); Order saved=orderRepo.save(order); cart.getItems().clear(); cartRepo.save(cart); return saved;
    }
}
