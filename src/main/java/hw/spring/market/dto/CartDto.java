package hw.spring.market.dto;

import hw.spring.market.model.Cart_v3;
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
        this.listItem = cart.getListItems().stream().map(CartItemDto::new).collect(Collectors.toList());
        this.totalCost = cart.getTotalCost();
        this.totalQuantity = cart.getTotalQuantity();
    }
}
