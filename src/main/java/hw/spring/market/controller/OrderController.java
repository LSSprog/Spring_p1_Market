package hw.spring.market.controller;

import hw.spring.market.dto.OrderDto;
import hw.spring.market.exeptionsHandling.ResourceNotFoundException;
import hw.spring.market.model.User;
import hw.spring.market.service.OrderService;
import hw.spring.market.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping("/create")
    public void createNewOrderFromCart(Principal principal) {
        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User for order not found"));
        orderService.createOrderFromCart(user, "без адреса");
    }

    @GetMapping
    public List<OrderDto> getUsersOrders(Principal principal) {
        return orderService.findAllOrdersByUsername(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @GetMapping("/create/{address}")
    public void createNewOrderFromCartWithAddress(@PathVariable String address, Principal principal) {
        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User for order not found"));
        orderService.createOrderFromCart(user, address);
    }



//    @PostMapping("/create")
}
