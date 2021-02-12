package hw.spring.market.service;

import hw.spring.market.beans.Cart_v2;
import hw.spring.market.model.Order;
import hw.spring.market.model.User;
import hw.spring.market.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final Cart_v2 cart;

    public Order createOrderFromCart(User user) {
        Order newOrder = new Order(cart, user);
        newOrder = orderRepository.save(newOrder); // очень странная запись - не понял её... в пред строке создали order, а теперь ещё раз "order ="
        cart.clear();
        return newOrder;
    }

    public List<Order> findAllOrdersByUsername(String username) {
        return orderRepository.findAllByUserUsername(username);
    }
}