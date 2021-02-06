package hw.spring.market.beans;

import hw.spring.market.exeptionsHandling.ResourceNotFoundException;
import hw.spring.market.model.OrderItem;
import hw.spring.market.model.Product;
import hw.spring.market.service.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart_v2 {
    private final ProductService productService;
    private List<OrderItem> listItems;
    private int totalQuantity;
    private int totalCost;

    @PostConstruct
    public void init() {
        this.listItems = new ArrayList<>();
    }

    public void addProductToCart (Long id) {
        for (OrderItem oi: listItems) {
            if (oi.getProduct().getId() == id) {
                oi.incQuantity();
                recalculate();
                return;
            }
        }
            Product product = productService.findProductById(id)
                    .orElseThrow(()->new ResourceNotFoundException("Not find product with id: " + id));
            OrderItem orderItem = new OrderItem(product);
            listItems.add(orderItem);
            recalculate();

    }

    private void recalculate() {
        totalCost = 0;
        totalQuantity = 0;
        for (OrderItem oi: listItems) {
            totalCost = totalCost + oi.getCost();
            totalQuantity = totalQuantity + oi.getQuantity();
        }
    }

    public void clear() {
        listItems.clear();
        recalculate();
    }

    public void deleteProductFromCart(Long id) {
        for (OrderItem oi: listItems) {
            if (oi.getProduct().getId() == id) {
                listItems.remove(oi);
                recalculate();
                return;
            }
        }
    }
}
