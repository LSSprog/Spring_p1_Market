package hw.spring.market.service;

import hw.spring.market.dto.ProductDto;
import hw.spring.market.model.Product;
import hw.spring.market.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<ProductDto> findProductDtoById(Long id) {
        return productRepository.findById(id).map(ProductDto::new);
//        Product product = productRepository.findById(id).get();
//        ProductDto productDto = new ProductDto(product);
//        return Optional.of(productDto);
    }
//
    public Page<ProductDto> findAll(Specification<Product> spec, Integer page, int sizeOfPage) {
        return productRepository.findAll(spec, PageRequest.of(page - 1, sizeOfPage)).map(ProductDto::new);
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }


    public ProductDto saveNewProduct(ProductDto productDto) {
        Product newProduct = new Product();
        newProduct.setTitle(productDto.getTitle());
        newProduct.setPrice(productDto.getPrice());
        productRepository.save(newProduct);
        productDto.setId(newProduct.getId());
        return productDto;
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }


    public ProductDto updateProduct (ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).get();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        productRepository.save(product); //здесь метод save обновит же имеющийся по ID, а не создаст новый - ДА
        return productDto;
    }

    //    public List<Product> findAllByPrice(Integer min, Integer max) {
//        return productRepository.findAllByPriceBetween(min, max);
//    }

//    public Page<Product> findAllByPages(Integer page) {
//        return productRepository.findAll(PageRequest.of((page - 1), 5)); //возвращаем страницы
//    }

//    public Page<ProductDto> findAllByPages(Integer page) {
//        Page<Product> originalPage = productRepository.findAll(PageRequest.of(page-1, 5));
//        return new PageImpl<>(originalPage.getContent().stream().map(ProductDto::new).collect(Collectors.toList()),
//                originalPage.getPageable(), originalPage.getTotalElements());
//    }
    //    public List<ProductDto> findAllProducts() {
//        return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
//    }

//    public Product saveOrUpdate(Product product) {
//        return productRepository.save(product);
//    }


}
