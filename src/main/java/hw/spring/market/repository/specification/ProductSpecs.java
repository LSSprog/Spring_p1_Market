package hw.spring.market.repository.specification;

import hw.spring.market.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;


public class ProductSpecs {
    private static Specification<Product> priceGreaterOrEqualsThan (int minPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice); // здесь беда ( не понял что написано, просто переписал
    }

    private static Specification<Product> priceLessOrEqualsThan (int mavPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("price"), mavPrice);
    }

    private static Specification<Product> titleLike(String titleText) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titleText));
    }

    public static Specification<Product> build(MultiValueMap<String, String> params) {

        Specification<Product> spec = Specification.where(null);
        if (params.containsKey("min_price") && !params.getFirst("min_price").isEmpty()) { //isBlank почему то не даёт выбрать! выбрал isEmpty
            spec = spec.and(ProductSpecs.priceGreaterOrEqualsThan(Integer.parseInt(params.getFirst("min_price"))));
        }
        if (params.containsKey("max_price") && !params.getFirst("max_price").isEmpty()) {
            spec = spec.and(ProductSpecs.priceLessOrEqualsThan(Integer.parseInt(params.getFirst("max_price"))));
        }
        if (params.containsKey("title") && !params.getFirst("title").isEmpty()) {
            spec = spec.and(ProductSpecs.titleLike(params.getFirst("title")));
        }
        return spec;
    }
}
