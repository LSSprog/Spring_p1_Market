package hw.spring.market.tests;

import hw.spring.market.model.Product;
import hw.spring.market.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test") //Ура! Нашёл где в моей Идее профили активировать. в Кофиг/Тэмплайтс/Мавен/Профили
public class RepoTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void getQuantityBase() {
//        List<Product> testList = productRepository.findAll();
//        Assertions.assertEquals(3, testList.size());
        List<Product> testMinMaxPrice = productRepository.findAllByPriceBetween(3000, 4000);
        Assertions.assertEquals(1, testMinMaxPrice.size());
    }
}
