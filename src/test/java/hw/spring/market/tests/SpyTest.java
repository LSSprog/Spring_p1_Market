package hw.spring.market.tests;

import hw.spring.market.dto.ProductDto;
import hw.spring.market.repository.ProductRepository;
import hw.spring.market.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SpyTest {
    @Autowired
    private ProductService productService;

    @Spy
    private List<ProductDto> dtoList = new ArrayList<>();

//    @MockBean
//    private ProductRepository productRepoMock;

    @Test
    public void spyTest() {  // ???Так и не получилось подменить size на 100 ((((

        dtoList = productService.getAllProductsForTest();

//        assertEquals(12, dtoList.size());

        Mockito.doReturn(100).when(dtoList).size();

        assertEquals(100, dtoList.size());

//        ProductDto testProDto = new ProductDto();
//        testProDto.setId(1L);
//        testProDto.setTitle("test");
//        testProDto.setPrice(300);
//        List<ProductDto> dtoList_1 = new ArrayList<>();
//        dtoList_1.add(testProDto);


//        Mockito.doReturn(dtoList_1)
//                .when(productRepoMock)
//                .findAll();

//        Mockito.verify(dtoList).add(testProDto);


    }
}
