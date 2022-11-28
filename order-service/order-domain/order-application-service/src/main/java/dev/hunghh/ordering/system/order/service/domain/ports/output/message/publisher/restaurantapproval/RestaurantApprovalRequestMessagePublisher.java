package dev.hunghh.ordering.system.order.service.domain.ports.output.message.publisher.restaurantapproval;

import dev.hunghh.ordering.system.order.service.domain.outbox.model.approval.OrderApprovalOutboxMessage;
import dev.hunghh.ordering.system.outbox.OutboxStatus;

import java.util.function.BiConsumer;

public interface RestaurantApprovalRequestMessagePublisher {

    void publish(OrderApprovalOutboxMessage orderApprovalOutboxMessage,
                 BiConsumer<OrderApprovalOutboxMessage, OutboxStatus> outboxCallback);
}
