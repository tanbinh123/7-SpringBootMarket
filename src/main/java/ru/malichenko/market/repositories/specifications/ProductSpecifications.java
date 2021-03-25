package ru.malichenko.market.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.malichenko.market.entities.ProductEntity;

public class ProductSpecifications {
    public static Specification<ProductEntity> priceGreaterOrEqualsThan(int minPrice) {// where b.price >= minPrice
        return (Specification<ProductEntity>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<ProductEntity> priceLesserOrEqualsThan(int maxPrice) {// where b.price <= maxPrice
        return (Specification<ProductEntity>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<ProductEntity> titleLike(String titlePart) {// where b.title like %titlePart%
        return (Specification<ProductEntity>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%",titlePart));
    }

    public static Specification<ProductEntity> categoryIdIs(Long category) {
        return (Specification<ProductEntity>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("categoryEntity").get("id"), category);
    }


}
