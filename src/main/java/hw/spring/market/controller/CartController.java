package hw.spring.market.controller;

import hw.spring.market.beans.Cart_v3;
import hw.spring.market.dto.CartDto;
import hw.spring.market.exeptionsHandling.ResourceNotFoundException;
import hw.spring.market.service.CartService;
import hw.spring.market.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping
    public UUID createNewCart() {
        Cart_v3 cart = cartService.save(new Cart_v3());
        return cart.getId();
    }

    @GetMapping("/{id}") // проверить работает ли так или {uuid} обязательно
    public CartDto getCurrentCart(@PathVariable UUID id) {
        Cart_v3 cart = cartService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Не найдена корзина с ID " + id));
        return new CartDto(cart);
    }

    @GetMapping("{uuid}/add/{product_id}")
    public void addProductToCart(@PathVariable UUID uuid, @PathVariable(name = "product_id") Long productID) {
        cartService.addToCart(uuid, productID);
    }


}
