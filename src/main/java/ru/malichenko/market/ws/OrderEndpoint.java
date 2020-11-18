package ru.malichenko.market.ws;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.malichenko.market.services.OrderService;
import ru.malichenko.market.ws.orders.GetOrdersByUserRequest;
import ru.malichenko.market.ws.orders.GetOrdersByUserResponse;


@Endpoint
@RequiredArgsConstructor
public class OrderEndpoint {
    private static final String NAMESPACE_URI = "http://www.malichenko.ru/market/ws/orders";

    private final OrderService orderService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrdersByUserRequest")
    @ResponsePayload
    public GetOrdersByUserResponse getOrdersByUserRequest(@RequestPayload GetOrdersByUserRequest request) {
        GetOrdersByUserResponse response = new GetOrdersByUserResponse();
        orderService.getAllSOAPOrdersByUsername(request.getName()).forEach(response.getAllOrders()::add);
        return response;
    }
}
//        public void setItems(List<OrderItem> items) {
//        this.items = items;
//    }