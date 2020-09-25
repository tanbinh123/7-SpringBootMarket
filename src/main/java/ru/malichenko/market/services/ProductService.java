package ru.malichenko.market.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.malichenko.market.dto.ProductDto;
import ru.malichenko.market.entities.Product;
import ru.malichenko.market.repositories.ProductRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<Product> findAll(Specification<Product> spec, int page, int size) {
        return productRepository.findAll(spec, PageRequest.of(page, size));
    }

//    public Page<Product> findAllByPriceGreaterThanEqualAndPriceLessThanEqual(Integer minPrice, Integer maxPrice, Pageable pages){
//        return productRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(minPrice,maxPrice, pages);
//    }
//    public List<Product> findAllByPriceBetween(Integer min, Integer max){
//        return productRepository.findAllByPriceBetween(min, max);
//    }

    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<ProductDto> findDtoById(Long id) {
        return productRepository.findOneById(id);
    }

    @Transactional
    public void saveOrUpdateProduct(Product modifiedProduct) {
        productRepository.saveAndFlush(modifiedProduct);
    }

    @Transactional
    public void deleteProductById(Long id) {
        productRepository.deleteProductById(id);
    }
}
