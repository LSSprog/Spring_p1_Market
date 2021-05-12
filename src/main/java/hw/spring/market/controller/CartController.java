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

    @GetMapping("/{uuid}") // проверить работает ли {id} так, или {uuid} обязательно
    public CartDto getCurrentCart(@PathVariable UUID uuid) {
        Cart_v3 cart = cartService.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Не найдена корзина с ID " + uuid));
        return new CartDto(cart);
    }

    @PostMapping("/add")
    public void addProductToCart(@RequestParam(name = "product_id") Long productID, @RequestParam UUID uuid) {
        cartService.addToCart(uuid, productID);
    }

    @PostMapping("/clear")
    public void clearCart(@RequestParam UUID cartUuid) {
        cartService.clearCart(cartUuid);
        //пытаюсь найти почему корзина не чиститься
        System.out.println("Корзина очищена:" + cartUuid);
        Cart_v3 cart = cartService.findById(cartUuid)
                .orElseThrow(() -> new ResourceNotFoundException("Не найдена корзина с ID " + cartUuid));
        int P = cart.getTotalCost();
        System.out.println(P);
    }

// Была первая версия удаления продукта из корзины, пишет не существует параметр productId
    @PostMapping("/delete")
    public void deleteProductFromCart(@RequestParam (name = "product_id") Long productId, @RequestParam UUID cartUuid) {
        cartService.deleteProductFromCart(cartUuid, productId);
    }

//    @DeleteMapping()
//    public void deleteProductFromCart(@RequestParam (name = "product_id") Long productId, @RequestParam UUID cartUuid) {
//        cartService.deleteProductFromCart(cartUuid, productId);
//    }


}
