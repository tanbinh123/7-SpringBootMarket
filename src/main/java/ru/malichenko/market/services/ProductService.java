package ru.malichenko.market.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.malichenko.market.dto.ProductDto;
import ru.malichenko.market.entities.Product;
import ru.malichenko.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Page<Product> findAll(int page, int size){
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public Optional<ProductDto> findDtoById(Long id){
        return productRepository.findOneById(id);
    }
}
