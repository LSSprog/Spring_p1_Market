package hw.spring.market.model;

import hw.spring.market.dto.ProductDto;
import hw.spring.market.service.ProductService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
//@RequiredArgsConstructor
public class Cart {
    private List<ProductDto> listProduct;
    //private ProductService productService; // почему здесь не сработал final - ругается

    @PostConstruct
    public void init(){
        listProduct = new ArrayList<ProductDto>();
    }

    public List<ProductDto> addProductToCart (ProductDto productDto) { /// или здесь void лучше сделать?
        listProduct.add(productDto);
        //listProduct.add(productService.findProductById(id).get());
        return listProduct;
    }
    public List<ProductDto> deleteProductFromCart (Long id) { /// или здесь void лучше сделать?
        //listProduct.remove(productService.findProductById(id).get());
        for (ProductDto p: listProduct) {
            if (p.getId() == id) {
                listProduct.remove(p);
            }
                    }
        return listProduct;
    }

    public Integer getTotalCost() {
        Integer totalCost = 0;
        for (ProductDto p: listProduct) {
            totalCost = totalCost + p.getPrice();
        }
        return totalCost; // TODO посчитать сумму по всему листу в позиции price
    }
}
