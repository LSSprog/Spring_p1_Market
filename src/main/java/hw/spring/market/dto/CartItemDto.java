package hw.spring.market.dto;

import hw.spring.market.model.CartItem;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemDto {
    private Long productId;
    private String title;
    private int quantity;
    private int price;
    private int cost;

    public CartItemDto(CartItem cartItem) {
        this.productId = cartItem.getProduct().getId();
        this.title = cartItem.getProduct().getTitle();
        this.quantity = cartItem.getQuantity();
        this.price = cartItem.getPrice();
        this.cost = cartItem.getCost();
    }

}
