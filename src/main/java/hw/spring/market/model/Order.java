package hw.spring.market.model;

import hw.spring.market.beans.Cart_v2;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table (name = "orders_tbl")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.ALL) // вроде здесь включили каскадное сохранение
    private List<OrderItem> items;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_quan_fld")
    private int totalQuantity;

    @Column(name = "total_cost_fld")
    private int cost;

    @Column(name = "address_fld")
    private String address;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Order(Cart_v2 cart, User user, String address) {
        this.items = new ArrayList<>();
        this.user = user;
        this.cost = cart.getTotalCost();
        this.totalQuantity = cart.getTotalQuantity();
        this.address = address;
        cart.getListItems().stream().forEach((orderItem) -> {
            orderItem.setOrder(this);
            items.add(orderItem);
        });
    }

}
