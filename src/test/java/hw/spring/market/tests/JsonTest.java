package hw.spring.market.tests;

import hw.spring.market.dto.ProductDto;
import hw.spring.market.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@org.springframework.boot.test.autoconfigure.json.JsonTest
public class JsonTest {
    @Autowired
    private JacksonTester<ProductDto> jacksonTester;

    @Test
    public void jsonSerTest() throws IOException {
        Product p = new Product();
        p.setId(5L);
        p.setTitle("Cross");
        p.setPrice(3900);
        ProductDto pDto = new ProductDto(p);

        assertThat(jacksonTester.write(pDto))
                .hasJsonPathStringValue("$.title")
                // Отвечали на лекции - Не выходит здесь так, нет метода greaterThen ? как написать проверку, что $.price > 3000 ?
                .extractingJsonPathNumberValue("$.id").isEqualTo(5); // здесь получается надо писать не 5L, а 5, в json всё числа просто

    }
}
