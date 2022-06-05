package dev.hunghh.ordering.system.payment.service.domain;

import dev.hunghh.ordering.system.payment.service.domain.dto.PaymentRequest;
import dev.hunghh.ordering.system.payment.service.domain.event.PaymentCancelledEvent;
import dev.hunghh.ordering.system.payment.service.domain.event.PaymentCompletedEvent;
import dev.hunghh.ordering.system.payment.service.domain.event.PaymentEvent;
import dev.hunghh.ordering.system.payment.service.domain.event.PaymentFailedEvent;
import dev.hunghh.ordering.system.payment.service.domain.ports.input.message.listener.PaymentRequestMessageListener;
import dev.hunghh.ordering.system.payment.service.domain.ports.output.message.publisher.PaymentCancelledMessagePublisher;
import dev.hunghh.ordering.system.payment.service.domain.ports.output.message.publisher.PaymentCompletedMessagePublisher;
import dev.hunghh.ordering.system.payment.service.domain.ports.output.message.publisher.PaymentFailedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentRequestMessageListenerImpl implements PaymentRequestMessageListener {

    private final PaymentRequestHelper paymentRequestHelper;

    public PaymentRequestMessageListenerImpl(PaymentRequestHelper paymentRequestHelper) {
        this.paymentRequestHelper = paymentRequestHelper;
    }

    @Override
    public void completePayment(PaymentRequest paymentRequest) {
        PaymentEvent paymentEvent = paymentRequestHelper.persistPayment(paymentRequest);
        fireEvent(paymentEvent);
    }

    @Override
    public void cancelPayment(PaymentRequest paymentRequest) {
        PaymentEvent paymentEvent = paymentRequestHelper.persistCancelPayment(paymentRequest);
        fireEvent(paymentEvent);;
    }

    private void fireEvent(PaymentEvent paymentEvent) {
        log.info("Publishing payment event with payment id: {} and order id: {}",
                paymentEvent.getPayment().getId().getValue(),
                paymentEvent.getPayment().getOrderId().getValue());
        paymentEvent.fire();
    }
}
