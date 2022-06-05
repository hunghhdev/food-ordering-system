package dev.hunghh.ordering.system.payment.service.domain.mapper;

import dev.hunghh.ordering.system.domain.valueobject.CustomerId;
import dev.hunghh.ordering.system.domain.valueobject.Money;
import dev.hunghh.ordering.system.domain.valueobject.OrderId;
import dev.hunghh.ordering.system.payment.service.domain.dto.PaymentRequest;
import dev.hunghh.ordering.system.payment.service.domain.entity.Payment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentDataMapper {
    public Payment paymentRequestModelToPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .orderId(new OrderId(UUID.fromString(paymentRequest.getId())))
                .customerId(new CustomerId(UUID.fromString(paymentRequest.getId())))
                .price(new Money(paymentRequest.getPrice()))
                .build();
    }
}
