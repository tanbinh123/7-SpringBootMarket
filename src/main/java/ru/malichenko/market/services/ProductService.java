package ru.malichenko.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.malichenko.market.entities.ProductEntity;
import ru.malichenko.market.repositories.ProductRepository;
import ru.malichenko.market.ws.products.Product;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    private  static final Function<ProductEntity, Product> functionEntityToSoap = (productEntity -> {
        Product product = new Product();
        product.setId(productEntity.getId());
        product.setTitle(productEntity.getTitle());
        product.setCategoryTitle(productEntity.getCategoryEntity().getTitle());
        product.setPrice(productEntity.getPrice());
        return product;
    });

    @Transactional(readOnly = true)
    public Page<ProductEntity> findAll(Specification<ProductEntity> spec, int page, int size) {
        return productRepository.findAll(spec, PageRequest.of(page, size));
    }

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<ProductEntity> findById(Long id) {
        return productRepository.findById(id);
    }

    public ProductEntity saveOrUpdate(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteProductEntityById(id);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    public List<Product> getAllSOAPProducts() {
        return productRepository.findAll().stream()
                .map(functionEntityToSoap)
                .collect(Collectors.toList());
    }

    public Product getSOAPProductByTitle(String title) {
        return productRepository.findByTitle(title).map(functionEntityToSoap).get();

    }
}

