package dev.hunghh.ordering.system.order.service.domain.outbox.scheduler.payment;

import dev.hunghh.ordering.system.order.service.domain.outbox.model.payment.OrderPaymentOutboxMessage;
import dev.hunghh.ordering.system.outbox.OutboxScheduler;
import dev.hunghh.ordering.system.outbox.OutboxStatus;
import dev.hunghh.ordering.system.saga.SagaStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PaymentOutboxCleanerScheduler implements OutboxScheduler {

    private final PaymentOutboxHelper paymentOutboxHelper;

    public PaymentOutboxCleanerScheduler(PaymentOutboxHelper paymentOutboxHelper) {
        this.paymentOutboxHelper = paymentOutboxHelper;
    }

    @Override
    @Scheduled(cron = "@midnight")
    public void processOutboxMessage() {
        Optional<List<OrderPaymentOutboxMessage>> outboxMessagesResponse =
                paymentOutboxHelper.getPaymentOutboxMessageByOutboxStatusAndSagaStatus(OutboxStatus.COMPLETED,
                SagaStatus.SUCCEEDED,
                SagaStatus.FAILED,
                SagaStatus.COMPENSATING);
        if (outboxMessagesResponse.isPresent()) {
            List<OrderPaymentOutboxMessage> outboxMessages = outboxMessagesResponse.get();
            log.info("Received {} OrderPaymentOutboxMessage for clean-up. The payloads: {}",
                    outboxMessages.size(),
                    outboxMessages.stream().map(OrderPaymentOutboxMessage::getPayload)
                            .collect(Collectors.joining("\n")));
            paymentOutboxHelper.deletePaymentOutboxMessageByOutboxStatusAndSagaStatus(OutboxStatus.COMPLETED,
                    SagaStatus.SUCCEEDED,
                    SagaStatus.FAILED,
                    SagaStatus.COMPENSATING);
            log.info("{} OrderPaymentOutboxMessage deleted!", outboxMessages.size());
        }
    }
}
