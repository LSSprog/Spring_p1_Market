package hw.spring.market.controller;

import hw.spring.market.model.Product;
import hw.spring.market.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> findAllProducts(
            @RequestParam(name = "min_price", defaultValue = "0") Integer min_price,
            @RequestParam(name = "max_price", required = false) Integer max_price
    ) {
        if (max_price == null) {
            max_price = Integer.MAX_VALUE;
        }

        //return productService.findAllProducts();
        return productService.findAllByPrice(min_price, max_price);
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable Long id) {
        return productService.findProductById(id).get();
    }

//    @GetMapping("/delete/{id}")
//    public void deleteProductById(@PathVariable Long id){
//        productService.deleteProductById(id);
//    }

    @DeleteMapping("/{id}")
    public void deleteProductBuId(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //хороший тон возвращать не 200 а ответ 201 - объект создан
    public Product saveNewProduct(@RequestBody Product product) {
        product.setId(null);
        return productService.saveOrUpdate(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.saveOrUpdate(product);
    }

}
