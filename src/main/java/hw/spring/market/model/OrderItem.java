package hw.spring.market.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table (name = "order_items_tbl")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id") // не нашёл связи в коде sql -куда это присоединятеся (((
    private Product product;

    @Column(name = "quantity_fld")
    private int quantity;

    @Column(name = "price_fld")
    private int price;

    @Column(name = "cost_fld")
    private int cost;

    public OrderItem(Product product) {
        this.product = product;
        this.quantity = 1;
        this.price = product.getPrice();
        this.cost = price;
    }

    public void incQuantity() {
        quantity++;
        cost = quantity * price;
    }

    public void decQuantity() {
        if (quantity > 0) {
            quantity--;
            cost = quantity * price;
        }
    }
}
