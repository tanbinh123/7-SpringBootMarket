package ru.malichenko.market.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.malichenko.market.dto.OrderDto;
import ru.malichenko.market.repositories.OrderRepository;
import ru.malichenko.market.ws.orders.GetOrdersByUserRequest;
import ru.malichenko.market.ws.orders.GetOrdersByUserResponse;
import ru.malichenko.market.ws.orders.Orders;

import java.util.List;
import java.util.stream.Collectors;


@Endpoint
public class OrderEndpoint {
    private static final String NAMESPACE_URI = "http://www.malichenko.ru/market/ws/orders";

    private final OrderRepository orderRepository;

    @Autowired
    public OrderEndpoint(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrdersByUserRequest")
    @ResponsePayload
    public GetOrdersByUserResponse getOrdersByUserRequest(@RequestPayload GetOrdersByUserRequest request) {
        GetOrdersByUserResponse response = new GetOrdersByUserResponse();
        List<Orders> listOrders = orderRepository.findAllOrdersByUsername(request.getName()).stream()
                .map(OrderDto::new)

                .map(Orders::new)
                .collect(Collectors.toList());
        //в коментарях сохранил то что добавил в авто генерируемые сущьности,
        //просто чтоб не набивать при их перегенерации
        //        public Orders() {
        //    }
        //
        //    public Orders(OrderDto orderDto) {
        //        this.items = orderDto.getItems().stream().map(OrderItem::new).collect(Collectors.toList());
        //        this.price = orderDto.getPrice();
        //        this.address = orderDto.getAddress();
        //    }
        //
        //    public OrderItem() {
        //    }
        //
        //    public OrderItem(OrderItemDto o){
        //        this.productId = o.getProductId();
        //        this.productTitle =o.getProductTitle();
        //        this.categoryTitle =o.getCategoryTitle();
        //        this.quantity = o.getQuantity();
        //        this.price = o.getPrice();
        //        this.pricePerProduct = o.getPricePerProduct();
        //    }
        response.setAllOrders(listOrders);
        //    public void setAllOrders(List<Orders> allOrders) {
        //        this.allOrders = allOrders;
        //    }
        return response;
    }
}
