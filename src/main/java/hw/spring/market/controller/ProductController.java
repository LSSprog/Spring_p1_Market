package hw.spring.market.controller;

import hw.spring.market.dto.ProductDto;
import hw.spring.market.exeptionsHandling.ResourceNotFoundException;
import hw.spring.market.model.Product;
import hw.spring.market.repository.specification.ProductSpecs;
import hw.spring.market.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<ProductDto> findAllProducts(
            @RequestParam MultiValueMap<String, String> params,
//            @RequestParam(name = "title", required = false) String title,
//            @RequestParam(name = "min_price", defaultValue = "0") Integer min_price,
//            @RequestParam(name = "max_price", required = false) Integer max_price,
            @RequestParam(name = "p", defaultValue = "1") Integer page
    ) {
//        if (max_price == null) {
//            max_price = Integer.MAX_VALUE;
//        }
        if (page < 1) {
            page = 1;
        }
        return productService.findAll(ProductSpecs.build(params), page, 2);
        //return productService.findAllProducts();
        //return productService.findAllByPrice(min_price, max_price);
        //return productService.findAllByPages(page); //.getContent(); //getContent - выдёргиваем из Page в List
    }

    @GetMapping("/{id}")
    public ProductDto findProductBuId(@PathVariable Long id) {
        return productService.findProductById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product with ID:" + id + " does not exist"));
    }
//    public Product findProductById(@PathVariable Long id) {
//        return productService.findProductById(id).get();
//    }

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
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        productDto.setId(null);
        return productService.saveNewProduct(productDto);
    }
//    public Product saveNewProduct(@RequestBody Product product) {
//        product.setId(null);
//        return productService.saveOrUpdate(product);
//    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto);
    }
//    public Product updateProduct(@RequestBody Product product) {
//        return productService.saveOrUpdate(product);
//    }

}
