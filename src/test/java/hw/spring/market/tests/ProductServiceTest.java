package hw.spring.market.tests;

import hw.spring.market.dto.ProductDto;
import hw.spring.market.model.Product;
import hw.spring.market.repository.ProductRepository;
import hw.spring.market.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest//(classes = ProductService.class)
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepoMock;

    @Test
    public void testGetProd() {
        Product testProduct = new Product();
        testProduct.setId(3L);
        testProduct.setTitle("LaW1");
        testProduct.setPrice(3500);

        Mockito.doReturn(Optional.of(testProduct))
                .when(productRepoMock)
                .findById(3L);

        Product p = productService.findProductById(3L).get();
        Assertions.assertEquals("LaW1", p.getTitle());
        Assertions.assertEquals(3500, p.getPrice());
    }

    @Test
    public void testUpdateProd() {
        Product testProduct = new Product();
        testProduct.setId(3L);
        testProduct.setTitle("LaW1");
        testProduct.setPrice(3500);

        ProductDto testProdDto = new ProductDto();
        testProdDto.setId(3L);
        testProdDto.setTitle("StM");
        testProdDto.setPrice(10000);

        Mockito.doReturn(Optional.of(testProduct))
                .when(productRepoMock)
                .findById(3L);

        ProductDto p = productService.updateProduct(testProdDto);
        Assertions.assertEquals("StM", p.getTitle());
        Assertions.assertEquals(10000, p.getPrice());

        Assertions.assertEquals("StM", testProduct.getTitle());


    }
}
