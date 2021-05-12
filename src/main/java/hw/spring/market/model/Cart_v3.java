package hw.spring.market.model;

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
@Table(name = "carts_tbl")
public class Cart_v3 {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "cart_id")
    private UUID id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> listItems;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "cost_fld")
    private int totalCost;

    @Column(name = "quantity_fld")
    private int totalQuantity;

    public void add(CartItem cartItem) {
        for (CartItem ci: this.listItems) {
            if (ci.getProduct().getId().equals(cartItem.getProduct().getId())) {
                ci.incQuantity(cartItem.getQuantity());
                recalculate();
                return;
            }
        }
        this.listItems.add(cartItem);
        cartItem.setCart(this);
        recalculate();
    }

    public void recalculate() {
        totalCost = 0;
        totalQuantity = 0;
        for (CartItem ci: this.listItems) {
            totalCost += ci.getCost();
            totalQuantity += ci.getQuantity();
        }
    }

    public void clear() {
        for (CartItem ci: this.listItems) {
            ci.setCart(null);
        }
        this.listItems.clear();
        recalculate();
        // удалить эту строку
        System.out.println("чисто в корзине" + this.id + " стоимость " + this.totalCost);
    }

    public void mergeCarts(Cart_v3 anotherCart) {
        for (CartItem ci: anotherCart.getListItems()) {
            add(ci);
        }
    }

    public CartItem getItemByProductId (Long productId) {
        for (CartItem ci:this.listItems) {
            if (ci.getProduct().getId().equals(productId)) {
                return ci;
            }
        }
        return null;
    }

    //по хорошему надо вернуть удаление товара из корзины целиком
    public void deleteItemByProductId (Long productId) {
        for (CartItem ci: this.listItems) {
            if (ci.getProduct().getId().equals(productId)) {
                ci.setCart(null);
            }
        }
        this.listItems.removeIf(p -> p.getProduct().getId().equals(productId));
        recalculate();
    }


}
