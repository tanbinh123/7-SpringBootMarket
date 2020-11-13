package ru.malichenko.market.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.malichenko.market.dto.ProductDto;
import ru.malichenko.market.repositories.ProductRepository;
import ru.malichenko.market.ws.products.GetAllProductsResponse;
import ru.malichenko.market.ws.products.GetProductByTitleRequest;
import ru.malichenko.market.ws.products.GetProductByTitleResponse;
import ru.malichenko.market.ws.products.Product;

import java.util.List;
import java.util.stream.Collectors;


@Endpoint
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.malichenko.ru/market/ws/products";

    private final ProductRepository productRepository;

    @Autowired
    public ProductEndpoint(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByTitleRequest")
    @ResponsePayload
    public GetProductByTitleResponse getProductByTitleRequest(@RequestPayload GetProductByTitleRequest request) {
        GetProductByTitleResponse response = new GetProductByTitleResponse();
        ProductDto productDto = new ProductDto(productRepository.findByTitle(request.getTitle()));
        Product product = new Product(productDto);
        //в коментарях сохранил то что добавил в авто генерируемые сущьности,
        //просто чтоб не набивать при их перегенерации
        //        public Product() {
        //    }
        //    public Product(ProductDto productDto) {
        //            this.id = productDto.getId();
        //            this.title = productDto.getTitle();
        //            this.price = productDto.getPrice();
        //            this.categoryTitle = productDto.getCategoryTitle();
        //        }
        response.setProduct(product);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProductsRequest() {
        GetAllProductsResponse response = new GetAllProductsResponse();
        List<Product> allProduct = productRepository.findAll().stream()
                .map(ProductDto::new)
                .map(Product::new)
                .collect(Collectors.toList());

        response.setAllProduct(allProduct); //добавил сеттер в GetAllProductsResponse
        //    public void setAllProduct(List<Product> allProduct) {
        //        this.allProduct = allProduct;
        //    }
        return response;
    }
}
