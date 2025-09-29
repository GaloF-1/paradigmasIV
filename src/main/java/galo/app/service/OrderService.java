package galo.app.service;
import galo.app.domain.Order;
import galo.app.repo.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service @Transactional
public class OrderService {
    private final OrderRepository orderRepo;
    public OrderService(OrderRepository o){
        this.orderRepo=o;
    }
    public Optional<Order> get(Long id){
        return orderRepo.findById(id);
    }
}
