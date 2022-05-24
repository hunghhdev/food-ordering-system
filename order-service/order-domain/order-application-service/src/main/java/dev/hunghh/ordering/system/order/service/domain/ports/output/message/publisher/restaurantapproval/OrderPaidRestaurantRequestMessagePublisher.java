package dev.hunghh.ordering.system.order.service.domain.ports.output.message.publisher.restaurantapproval;

import dev.hunghh.ordering.system.domain.event.publisher.DomainEventPublisher;
import dev.hunghh.ordering.system.order.service.domain.event.OrderPaidEvent;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
