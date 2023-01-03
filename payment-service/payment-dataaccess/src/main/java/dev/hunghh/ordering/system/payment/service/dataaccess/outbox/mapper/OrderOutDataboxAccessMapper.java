package dev.hunghh.ordering.system.payment.service.dataaccess.outbox.mapper;

import dev.hunghh.ordering.system.domain.valueobject.CustomerId;
import dev.hunghh.ordering.system.domain.valueobject.Money;
import dev.hunghh.ordering.system.domain.valueobject.OrderId;
import dev.hunghh.ordering.system.payment.service.dataaccess.outbox.entity.OrderOutboxEntity;
import dev.hunghh.ordering.system.payment.service.domain.outbox.model.OrderOutboxMessage;
import dev.hunghh.ordering.system.payment.service.domain.valueobject.PaymentId;
import org.springframework.stereotype.Component;

@Component
public class OrderOutDataboxAccessMapper {

    public OrderOutboxEntity orderOutboxMessageToOrderOutboxEntity(OrderOutboxMessage orderOutboxMessage) {
        return OrderOutboxEntity.builder()
                .id(orderOutboxMessage.getId())
                .sagaId(orderOutboxMessage.getSagaId())
                .createdAt(orderOutboxMessage.getCreatedAt())
                .type(orderOutboxMessage.getType())
                .payload(orderOutboxMessage.getPayload())
                .outboxStatus(orderOutboxMessage.getOutboxStatus())
                .paymentStatus(orderOutboxMessage.getPaymentStatus())
                .version(orderOutboxMessage.getVersion())
                .build();
    }

    public OrderOutboxMessage orderOutboxEntityToOrderOutboxMessage(OrderOutboxEntity orderOutboxEntity) {
        return OrderOutboxMessage.builder()
                .id(orderOutboxEntity.getId())
                .sagaId(orderOutboxEntity.getSagaId())
                .createdAt(orderOutboxEntity.getCreatedAt())
                .type(orderOutboxEntity.getType())
                .payload(orderOutboxEntity.getPayload())
                .outboxStatus(orderOutboxEntity.getOutboxStatus())
                .paymentStatus(orderOutboxEntity.getPaymentStatus())
                .version(orderOutboxEntity.getVersion())
                .build();
    }
}
