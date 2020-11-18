package ru.malichenko.market.ws;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.malichenko.market.services.ProductService;
import ru.malichenko.market.ws.products.GetAllProductsResponse;
import ru.malichenko.market.ws.products.GetProductByTitleRequest;
import ru.malichenko.market.ws.products.GetProductByTitleResponse;


@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.malichenko.ru/market/ws/products";

    private final ProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByTitleRequest")
    @ResponsePayload
    public GetProductByTitleResponse getProductByTitleRequest(@RequestPayload GetProductByTitleRequest request) {
        GetProductByTitleResponse response = new GetProductByTitleResponse();
        response.setProduct(productService.getSOAPProductByTitle(request.getTitle()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProductsRequest() {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.getAllSOAPProducts().forEach(response.getAllProduct()::add);
        return response;
    }
}