package hw.spring.market.dto;

import hw.spring.market.beans.Cart_v2;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CartDto {
    private List<OrderItemDto> listItem;
    private int totalCost;
    private int totalQuantity;

    public CartDto(Cart_v2 cart) {
        this.listItem = cart.getListItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.totalCost = cart.getTotalCost();
        this.totalQuantity = cart.getTotalQuantity();
    }
}
