package hw.spring.market.service;

import hw.spring.market.model.Cart_v3;
import hw.spring.market.exeptionsHandling.ResourceNotFoundException;
import hw.spring.market.model.CartItem;
import hw.spring.market.model.Product;
import hw.spring.market.model.User;
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
    private final UserService userService;

    public Cart_v3 save(Cart_v3 cart) {
        return cartRepository.save(cart);
    }

    public Optional<Cart_v3> findById(UUID id) {
        return cartRepository.findById(id);
    }

    @Transactional
    public void addToCart(UUID cartId, Long productId) {
        Cart_v3 cart = findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Не найдена корзина с ID " + cartId));
        CartItem cartItem = cart.getItemByProductId(productId);
        if (cartItem != null) {
            cartItem.incQuantity();
            cart.recalculate();
        } else {
            Product product = productService.findProductById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException("Продукт с ID " + productId + "не может быть добавлен в корзину"));
            cart.add(new CartItem(product));
        }
    }

    public void clearCart(UUID cartId) {
        Cart_v3 cart = findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Не найдена корзина с ID " + cartId));
        cart.clear();
    }

    public Optional<Cart_v3> findByUserId(Long id) {
        return cartRepository.findByUserId(id);
    }

    @Transactional
    public UUID getCartForUser(String username, UUID cartId) {
        if (username != null && cartId != null) {
            User user = userService.findByUsername(username).get();
            Cart_v3 cart = findById(cartId).get();
            Optional<Cart_v3> oldCart = findByUserId(user.getId());
            if (oldCart.isPresent()) {
                cart.mergeCarts(oldCart.get());
                cartRepository.delete(oldCart.get());
            }
            cart.setUser(user);
            // ничего не возвращает этот вариант
        }
        if (username == null) {
            Cart_v3 cart = new Cart_v3();
            save(cart);
            return cart.getId();
        }
        User user = userService.findByUsername(username).get(); //задвоение операций получается, если выполнено первое условие
        Optional<Cart_v3> cart = findByUserId(user.getId());
        if (cart.isPresent()) {
            return cart.get().getId();
        }
        Cart_v3 newCart = new Cart_v3();
        newCart.setUser(user);
        save(newCart);
        return newCart.getId();
    }

    public void deleteProductFromCart(UUID cartId, Long productId) {
        Cart_v3 cart = findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Не найдена корзина с ID " + cartId));
        cart.deleteItemByProductId(productId);
    }

    // Посмотреть как оптимизировать этот код на втором варианте
//    public UUID getCartFoUser_v2 (String username, UUID cartId) {
//        if (username == null) {
//            Cart_v3 cart = new Cart_v3();
//            save(cart);
//            return cart.getId();
//        } else {
//            User user = userService.findByUsername(username).get();
//            if (cartId != null) {
//                Cart_v3 cart = findById(cartId).get();
//                Optional<Cart_v3> oldCart = findByUserId(user.getId());
//                if (oldCart.isPresent()) {
//                    cart.mergeCarts(oldCart.get());
//                    cartRepository.delete(oldCart.get());
//                }
//                cart.setUser(user);
//            } else {
//                Optional<Cart_v3> cart = findByUserId(user.getId());
//                if (cart.isPresent()) {
//                    return cart.get().getId();
//                }
//                Cart_v3 newCart = new Cart_v3();
//                newCart.setUser(user);
//                save(newCart);
//                return newCart.getId();
//            }
//        }
//    }

}
