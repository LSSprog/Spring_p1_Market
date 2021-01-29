package hw.spring.market.controller;

import hw.spring.market.dto.ProductDto;
import hw.spring.market.model.Cart;
import hw.spring.market.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cart")

public class CartController {
    private final Cart cart;
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> showCart() {
        return cart.getListProduct();
    }

    @GetMapping("/add/{id}")
    public List<ProductDto> addProductToCart(@PathVariable Long id){
        //ProductDto productDto = productService.findProductById(id).get();
        return cart.addProductToCart(productService.findProductById(id).get());
    }

    @GetMapping("/delete/{id}")
    public List<ProductDto> deleteProductFromCart(@PathVariable Long id) {
        return cart.deleteProductFromCart(id);
    }
}
