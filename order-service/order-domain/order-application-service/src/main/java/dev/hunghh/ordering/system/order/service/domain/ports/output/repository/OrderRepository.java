package dev.hunghh.ordering.system.order.service.domain.ports.output.repository;

import dev.hunghh.ordering.system.domain.valueobject.OrderId;
import dev.hunghh.ordering.system.order.service.domain.entity.Order;
import dev.hunghh.ordering.system.order.service.domain.valueobject.TrackingId;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);
    Optional<Order> findById(OrderId orderId);
    Optional<Order> findByTrackingId(TrackingId trackingId);
}
