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

    public Order createOrderFromCart(User user, String address) {
        Order newOrder = new Order(cart, user, address);
        newOrder = orderRepository.save(newOrder);
        cart.clear();
        return newOrder;
    }

    public List<Order> findAllOrdersByUsername(String username) {
        return orderRepository.findAllByUserUsername(username);
    }
}
