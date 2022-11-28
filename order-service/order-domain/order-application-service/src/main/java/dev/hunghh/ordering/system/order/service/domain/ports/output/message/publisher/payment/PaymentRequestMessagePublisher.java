package dev.hunghh.ordering.system.order.service.domain.ports.output.message.publisher.payment;

import dev.hunghh.ordering.system.order.service.domain.outbox.model.payment.OrderPaymentOutboxMessage;
import dev.hunghh.ordering.system.outbox.OutboxStatus;

import java.util.function.BiConsumer;

public interface PaymentRequestMessagePublisher {

    void publish(OrderPaymentOutboxMessage orderPaymentOutboxMessage,
                 BiConsumer<OrderPaymentOutboxMessage, OutboxStatus> outboxCallback);
}
