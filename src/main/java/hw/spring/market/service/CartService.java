package hw.spring.market.service;

import hw.spring.market.beans.Cart_v3;
import hw.spring.market.exeptionsHandling.ResourceNotFoundException;
import hw.spring.market.model.CartItem;
import hw.spring.market.model.Product;
import hw.spring.market.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;

    public Cart_v3 save(Cart_v3 cart) {
        return cartRepository.save(cart);
    }

    public Optional<Cart_v3> findById(UUID id) {
        return cartRepository.findById(id);
    }

    @Transactional
    public void addToCart(UUID cartId, Long productId) {
        Product product = productService.findProductById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт с ID " + productId + "не может быть добавлен в корзину"));
        Cart_v3 cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Не найдена корзина с ID " + cartId));
        cart.add(new CartItem(product));
    }

}
