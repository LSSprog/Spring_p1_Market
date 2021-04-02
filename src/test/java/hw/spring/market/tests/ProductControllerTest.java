package hw.spring.market.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

/// ОБЯЗАТЕЛЬНО добавить для проверок
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void findAllProductsTestDb() throws Exception {
        mvc.perform(get("/api/v1/products")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.content", hasSize(3)))
            .andExpect(jsonPath("$.content[1].title", is("StM")))
            .andExpect(jsonPath("$.content[0].price", lessThan(3000)));


    }

    @Test
    public void findAllProductsByPriceFilter() throws Exception {
        Integer min_price = 3000;
        Integer max_price = 4000;
        mvc.perform(get("/api/v1/products?min_price=" + min_price + "&max_price=" + max_price)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].price", is(3200)));

    }
}
