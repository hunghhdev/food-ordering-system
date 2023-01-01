package dev.hunghh.ordering.system.payment.service.domain.ports.output.message.publisher;

import dev.hunghh.ordering.system.outbox.OutboxStatus;
import dev.hunghh.ordering.system.payment.service.domain.outbox.model.OrderOutboxMessage;

import java.util.function.BiConsumer;

public interface PaymentResponseMessagePublisher {
    void publisher(OrderOutboxMessage orderOutboxMessage,
                   BiConsumer<OrderOutboxMessage, OutboxStatus> outboxCallback);
}
