package hw.spring.market.service;

import hw.spring.market.Old.Cart_v2;
import hw.spring.market.exeptionsHandling.ResourceNotFoundException;
import hw.spring.market.model.Cart_v3;
import hw.spring.market.model.Order;
import hw.spring.market.model.User;
import hw.spring.market.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private UserService userService;

    @Transactional
    public Order createOrderFromUserCart(String username, UUID cartId, String address) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с именем " + username + " не найден"));
        Cart_v3 cart = cartService.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Корзины с id " + cartId + " не существует"));
        Order order = new Order(cart, user, address);
        order = orderRepository.save(order);
        return order;
    }


    public List<Order> findAllOrdersByUsername(String username) {
        return orderRepository.findAllByUserUsername(username);
    }

    public Optional<Order> findOrderById(Long id) {
        return orderRepository.findById(id);
    }

//    public Order createOrderFromCart(User user, String address) {
//        Order newOrder = new Order(cart, user, address);
//        newOrder = orderRepository.save(newOrder);
//        cart.clear();
//        return newOrder;

}
