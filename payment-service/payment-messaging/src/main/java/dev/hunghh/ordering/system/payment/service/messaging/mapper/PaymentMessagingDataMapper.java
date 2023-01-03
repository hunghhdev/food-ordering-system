package dev.hunghh.ordering.system.payment.service.messaging.mapper;

import dev.hunghh.ordering.system.domain.valueobject.PaymentOrderStatus;
import dev.hunghh.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import dev.hunghh.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel;
import dev.hunghh.ordering.system.kafka.order.avro.model.PaymentStatus;
import dev.hunghh.ordering.system.payment.service.domain.dto.PaymentRequest;
import dev.hunghh.ordering.system.payment.service.domain.event.PaymentCancelledEvent;
import dev.hunghh.ordering.system.payment.service.domain.event.PaymentCompletedEvent;
import dev.hunghh.ordering.system.payment.service.domain.event.PaymentFailedEvent;
import dev.hunghh.ordering.system.payment.service.domain.outbox.model.OrderEventPayload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentMessagingDataMapper {

    public PaymentRequest paymentRequestAvroModelToPaymentRequest(PaymentRequestAvroModel paymentRequestAvroModel) {
        return PaymentRequest.builder()
                .id(paymentRequestAvroModel.getId())
                .sagaId(paymentRequestAvroModel.getSagaId())
                .customerId(paymentRequestAvroModel.getCustomerId())
                .orderId(paymentRequestAvroModel.getOrderId())
                .price(paymentRequestAvroModel.getPrice())
                .createdAt(paymentRequestAvroModel.getCreatedAt())
                .paymentOrderStatus(PaymentOrderStatus.valueOf(paymentRequestAvroModel.getPaymentOrderStatus().name()))
                .build();
    }

    public PaymentResponseAvroModel orderEventPayloadToPaymentResponseAvroModel(String sagaId,
                                                                                OrderEventPayload orderEventPayload) {
        return PaymentResponseAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId(sagaId)
                .setPaymentId(orderEventPayload.getPaymentId())
                .setCustomerId(orderEventPayload.getCustomerId())
                .setOrderId(orderEventPayload.getOrderId())
                .setPrice(orderEventPayload.getPrice())
                .setCreatedAt(orderEventPayload.getCreatedAt().toInstant())
                .setPaymentStatus(PaymentStatus.valueOf(orderEventPayload.getPaymentStatus()))
                .setFailureMessages(orderEventPayload.getFailureMessages())
                .build();
    }
}
