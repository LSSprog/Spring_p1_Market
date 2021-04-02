package hw.spring.market.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cart_items_tbl")
public class CartItem {

    @Id
    @Column(name = "cart_items_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart_v3 cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "title_fld")
    private String title;

    @Column(name = "quantity_fld")
    private int quantity;

    @Column(name = "price_fld")
    private int price;

    @Column(name = "cost_fld")
    private int cost;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
        this.price = product.getPrice();
        this.cost = this.price;
    }

    // посмотреть может этот метод уже и не нужен, если есть с кол-вом
    public void incQuantity() {
        this.quantity++;
        this.cost = this.quantity * this.price;
    }

    public void incQuantity(int num) {
        this.quantity += num;
        recalculateCartItemCost();
    }

    public void decQuantity() {
        if (quantity > 0) {
            this.quantity--;
            recalculateCartItemCost();
        }
    }

    private void recalculateCartItemCost() {
        this.cost = this.quantity * this.price;
    }



}
