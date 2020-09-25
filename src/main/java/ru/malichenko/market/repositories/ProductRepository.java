package ru.malichenko.market.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.malichenko.market.dto.ProductDto;
import ru.malichenko.market.entities.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Optional<ProductDto> findOneById(Long id);

    Page<Product> findAllByPriceGreaterThanEqualAndPriceLessThanEqual(Integer minPrice, Integer maxPrice, Pageable pageable);

    List<Product> findAllByPriceBetween(Integer minPrice, Integer maxPrice);

    Product save(Product modifiedProduct);

    void deleteProductById(Long id);
}
