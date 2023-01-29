package dev.hunghh.ordering.system.restaurant.service.domain.ports.output.message.publisher;

import dev.hunghh.ordering.system.outbox.OutboxStatus;
import dev.hunghh.ordering.system.restaurant.service.domain.outbox.model.OrderOutboxMessage;

import java.util.function.BiConsumer;

public interface RestaurantApprovalResponseMessagePublisher {
    void publish(OrderOutboxMessage orderOutboxMessage,
                 BiConsumer<OrderOutboxMessage, OutboxStatus> outboxCallback);
}
