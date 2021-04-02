package hw.spring.market.beans;

import hw.spring.market.model.CartItem;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "carts")
public class Cart_v3 {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "cart_id")
    private UUID id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> items;

    @Column(name = "cost_fld")
    private int cost;

    @Column(name = "quantity_fld")
    private int quantity;

    public void add(CartItem cartItem) {
        this.items.add(cartItem);
        cartItem.setCart(this);
        recalculate();
    }

    private void recalculate() {
        cost = 0;
        for (CartItem ci: items) {
            cost += ci.getCost();
        }
    }
}
