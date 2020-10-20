package ru.malichenko.market.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteProductById(id);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public boolean existsById(Long id){
        return  productRepository.existsById(id);
    }

}

