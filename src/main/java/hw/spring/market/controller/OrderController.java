package hw.spring.market.controller;

import hw.spring.market.dto.OrderDto;
import hw.spring.market.exeptionsHandling.ResourceNotFoundException;
import hw.spring.market.model.Order;
import hw.spring.market.model.User;
import hw.spring.market.service.CartService;
import hw.spring.market.service.OrderService;
import hw.spring.market.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final CartService cartService;

    @GetMapping
    public List<OrderDto> getUsersOrders(Principal principal) {
        return orderService.findAllOrdersByUsername(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createNewOrderFromCartWithAddress(Principal principal, @RequestParam UUID cartId, @RequestParam String address) {
//        User user = userService.findByUsername(principal.getName())
//                .orElseThrow(() -> new ResourceNotFoundException("User for order not found"));
        Order order = orderService.createOrderFromUserCart(principal.getName(), cartId, address);
        cartService.clearCart(cartId);
        return new OrderDto(order);
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        Order order = orderService.findOrderById(id).orElseThrow(()-> new ResourceNotFoundException("Order not found"));
        return new OrderDto(order);
    }
//    @GetMapping("/create")
//    public void createNewOrderFromCart(Principal principal) {
//        User user = userService.findByUsername(principal.getName())
//                .orElseThrow(() -> new ResourceNotFoundException("User for order not found"));
//        orderService.createOrderFromCart(user, "без адреса");
//    }

//    @GetMapping("/create/{address}")
//    public void createNewOrderFromCartWithAddress(@PathVariable String address, Principal principal) {
//        User user = userService.findByUsername(principal.getName())
//                .orElseThrow(() -> new ResourceNotFoundException("User for order not found"));
//        orderService.createOrderFromCart(user, address);
//    }

}
