package dev.hunghh.ordering.system.restaurant.service.domain.ports.output.message.publisher;

import dev.hunghh.ordering.system.domain.event.publisher.DomainEventPublisher;
import dev.hunghh.ordering.system.restaurant.service.domain.event.OrderRejectedEvent;

public interface OrderRejectedMessagePublisher extends DomainEventPublisher<OrderRejectedEvent> {
}
