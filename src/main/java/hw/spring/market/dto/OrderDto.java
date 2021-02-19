package hw.spring.market.dto;

import hw.spring.market.model.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String username;
    private int totalCost;
    private int totalQuantity;
    private String createdDateTime;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.username = order.getUser().getUsername();
        this.totalCost = order.getCost();
        this.totalQuantity = order.getTotalQuantity();
        this.createdDateTime = order.getCreatedAt().toString();
    }
}
