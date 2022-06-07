package dev.hunghh.ordering.system.restaurant.service.domain.mapper;

import dev.hunghh.ordering.system.domain.valueobject.Money;
import dev.hunghh.ordering.system.domain.valueobject.OrderId;
import dev.hunghh.ordering.system.domain.valueobject.OrderStatus;
import dev.hunghh.ordering.system.domain.valueobject.RestaurantId;
import dev.hunghh.ordering.system.restaurant.service.domain.dto.RestaurantApprovalRequest;
import dev.hunghh.ordering.system.restaurant.service.domain.entity.OrderDetail;
import dev.hunghh.ordering.system.restaurant.service.domain.entity.Product;
import dev.hunghh.ordering.system.restaurant.service.domain.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantDataMapper {

    public Restaurant restaurantApprovalRequestToRestaurant(RestaurantApprovalRequest
                                                                     restaurantApprovalRequest) {
        return Restaurant.builder()
                .restaurantId(new RestaurantId(UUID.fromString(restaurantApprovalRequest.getRestaurantId())))
                .orderDetail(OrderDetail.builder()
                        .orderId(new OrderId(UUID.fromString(restaurantApprovalRequest.getOrderId())))
                        .products(restaurantApprovalRequest.getProducts().stream().map(
                                product -> Product.builder()
                                        .productId(product.getId())
                                        .quantity(product.getQuantity())
                                        .build())
                                .collect(Collectors.toList()))
                        .totalAmount(new Money(restaurantApprovalRequest.getPrice()))
                        .orderStatus(OrderStatus.valueOf(restaurantApprovalRequest.getRestaurantOrderStatus().name()))
                        .build())
                .build();
    }
}
