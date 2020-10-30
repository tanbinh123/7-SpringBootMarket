package ru.malichenko.market.utils;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import ru.malichenko.market.entities.Product;
import ru.malichenko.market.repositories.specifications.ProductSpecifications;

import java.util.Map;

@Getter
public class ProductFilter {
    private Specification<Product> spec;

    public ProductFilter(Map<String, String> params) {
        spec = Specification.where(null);

        String filterTitle = params.get("title");
        if (filterTitle != null && !filterTitle.isBlank()) {
            spec = spec.and(ProductSpecifications.titleLike(filterTitle));
        }

        if (params.containsKey("category") && !params.get("category").isBlank()) {
            Long filterCategory = Long.parseLong(params.get("category"));
            spec = spec.and(ProductSpecifications.categoryIdIs(filterCategory));
        }

        if (params.containsKey("min_price") && !params.get("min_price").isBlank()) {
            Integer minPrice = Integer.parseInt(params.get("min_price"));
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }

        if (params.containsKey("max_price") && !params.get("max_price").isBlank()) {
            Integer maxPrice = Integer.parseInt(params.get("max_price"));
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
        }
    }
}
