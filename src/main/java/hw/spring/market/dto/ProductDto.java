package hw.spring.market.dto;

import hw.spring.market.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private int price;

    public ProductDto(Product p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.price = p.getPrice();
    }
}
