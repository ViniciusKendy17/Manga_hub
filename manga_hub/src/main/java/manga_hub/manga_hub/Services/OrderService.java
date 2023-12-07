package manga_hub.manga_hub.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import manga_hub.manga_hub.models.OrderModel;
import manga_hub.manga_hub.models.UserModel;
import manga_hub.manga_hub.repositories.OrderRepository;
import manga_hub.manga_hub.repositories.UserRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    
    public OrderModel createOrder(OrderModel order) {
        return repository.save(order);
    }

}
