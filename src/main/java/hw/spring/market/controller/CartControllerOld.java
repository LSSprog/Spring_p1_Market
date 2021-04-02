package hw.spring.market.controller;

import hw.spring.market.beans.Cart_v2;
import hw.spring.market.dto.CartDto;
import hw.spring.market.dto.ProductDto;
import hw.spring.market.beans.CartOld;
import hw.spring.market.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/cart")

public class CartControllerOld {
//    private final Cart_v2 cart;
//    private final ProductService productService;
//
//    @GetMapping
//    public CartDto showCart() {
//        return new CartDto(cart);
//    }
//
//    @GetMapping("/add/{id}")
//    public void addProductToCart(@PathVariable Long id){
//        cart.addProductToCart(id);
//        //return new CartDto(cart);
//        //ProductDto productDto = productService.findProductById(id).get();
//        //return cart.addProductToCart(productService.findProductById(id).get());
//    }
//
//    @GetMapping("/delete/{id}")
//    public void deleteProductFromCart(@PathVariable Long id) {
//        cart.deleteProductFromCart(id);
//    }
//
//    @GetMapping("/clear")
//    public CartDto clearCart() {
//        cart.clear();
//        return new CartDto(cart);
//    }
//
//    @GetMapping("/inc/{id}")
//    public void incQuantity(@PathVariable Long id) {
//        cart.addProductToCart(id);
//    }
}
