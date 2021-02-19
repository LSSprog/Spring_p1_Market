package hw.spring.market;

import hw.spring.market.configs.AppConfig;
import hw.spring.market.dto.ProductDto;
import hw.spring.market.model.User;
import hw.spring.market.service.ProductService;
import hw.spring.market.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:secret.properties") //secret берётся не из yaml общего, а из отдельного файла, для безопасности
public class MarketSsApplication {

	// Домашнее задание:
	// 1. Сделайте профилировщик, который подсчитывает какой метод в проекте
	// используется чаще всего
	// 2. * Попробуйте подсчитать методы какого контроллера уходит больше всего времени

	public static void main(String[] args) {
		SpringApplication.run(MarketSsApplication.class, args);
//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//		ProductService productService = context.getBean("productService", ProductService.class);
//		ProductDto productDto = productService.findProductDtoById(1L).get();
//		System.out.println(productDto.getTitle());
//
//		context.close();

	}

}
