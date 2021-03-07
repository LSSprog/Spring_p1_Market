package hw.spring.market.dto;

import hw.spring.market.model.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long product_id;
    private String title;
    private int quantity;
    private int price;
    private int cost;

    public OrderItemDto(OrderItem orderItem) {
        this.product_id = orderItem.getProduct().getId();
        this.title = orderItem.getProduct().getTitle();
        this.quantity = orderItem.getQuantity();
        this.price = orderItem.getPrice();
        this.cost = orderItem.getCost();
    }
}
