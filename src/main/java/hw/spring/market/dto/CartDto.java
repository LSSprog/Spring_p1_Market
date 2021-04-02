package hw.spring.market.dto;

import hw.spring.market.beans.Cart_v2;
import hw.spring.market.beans.Cart_v3;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CartDto {
    private List<CartItemDto> listItem;
    private int totalCost;
    private int totalQuantity;

    public CartDto(Cart_v3 cart) {
        this.listItem = cart.getItems().stream().map(CartItemDto::new).collect(Collectors.toList());
        this.totalCost = cart.getCost();
        this.totalQuantity = cart.getQuantity();
    }
}
