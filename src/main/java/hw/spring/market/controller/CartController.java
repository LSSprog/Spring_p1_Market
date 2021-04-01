package hw.spring.market.controller;

import hw.spring.market.model.Cart_v3;
import hw.spring.market.dto.CartDto;
import hw.spring.market.exeptionsHandling.ResourceNotFoundException;
import hw.spring.market.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping
    public UUID createNewCart(Principal principal) {
        if (principal == null) {
            return cartService.getCartForUser(null, null);
        }
        return cartService.getCartForUser(principal.getName(), null);
    }

    @GetMapping("/{id}") // проверить работает ли так или {uuid} обязательно
    public CartDto getCurrentCart(@PathVariable UUID id) {
        Cart_v3 cart = cartService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Не найдена корзина с ID " + id));
        return new CartDto(cart);
    }

    @PostMapping("/add")
    public void addProductToCart(@PathVariable UUID uuid, @PathVariable(name = "product_id") Long productID) {
        cartService.addToCart(uuid, productID);
    }

    @PostMapping("/clear")
    public void clearCart(@RequestParam UUID cartId) {
        cartService.clearCart(cartId);
    }


}
