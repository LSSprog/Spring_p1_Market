package hw.spring.market.service;

import hw.spring.market.dto.ProductDto;
import hw.spring.market.model.Product;
import hw.spring.market.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<ProductDto> findProductById(Long id) {
        Product product = productRepository.findById(id).get();
        ProductDto productDto = new ProductDto(product);
        return Optional.of(productDto);
    }
//    public Optional<Product> findProductById(Long id) {
//        return productRepository.findById(id);
//    }


    public List<ProductDto> findAllProducts() {
        return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findAllByPrice(Integer min, Integer max) {
        return productRepository.findAllByPriceBetween(min, max);
    }

//    public Page<Product> findAllByPages(Integer page) {
//        return productRepository.findAll(PageRequest.of((page - 1), 5)); //возвращаем страницы
//    }
    public Page<ProductDto> findAllByPages(Integer page) {
        Page<Product> originalPage = productRepository.findAll(PageRequest.of(page-1, 5));
        return new PageImpl<>(originalPage.getContent().stream().map(ProductDto::new).collect(Collectors.toList()),
                originalPage.getPageable(), originalPage.getTotalElements());
    }
}
