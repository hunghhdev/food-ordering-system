package dev.hunghh.ordering.system.payment.service.domain;

import dev.hunghh.ordering.system.domain.valueobject.PaymentOrderStatus;
import dev.hunghh.ordering.system.domain.valueobject.PaymentStatus;
import dev.hunghh.ordering.system.outbox.OutboxStatus;
import dev.hunghh.ordering.system.payment.service.dataaccess.outbox.entity.OrderOutboxEntity;
import dev.hunghh.ordering.system.payment.service.dataaccess.outbox.repository.OrderOutboxJpaRepository;
import dev.hunghh.ordering.system.payment.service.domain.dto.PaymentRequest;
import dev.hunghh.ordering.system.payment.service.domain.ports.input.message.listener.PaymentRequestMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static dev.hunghh.ordering.system.saga.order.SagaConstants.ORDER_SAGA_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest(classes = PaymentServiceApplication.class)
public class PaymentRequestMessageListenerTest {

    @Autowired
    private PaymentRequestMessageListener paymentRequestMessageListener;
    @Autowired
    private OrderOutboxJpaRepository orderOutboxJpaRepository;

    private final static String CUSTOMER_ID = "44494d1f-6451-4073-9345-1a5ec34b4941";
    private final static BigDecimal PRICE = new BigDecimal("100");

    @Test
    void testDoublePayment() {
        String sagaId = UUID.randomUUID().toString();
        paymentRequestMessageListener.completePayment(getPaymentRequest(sagaId));
        try {
            paymentRequestMessageListener.completePayment(getPaymentRequest(sagaId));
        } catch (DataAccessException e) {
            log.error("DataAccessException occurred with sql state: {}",
                    ((PSQLException) Objects.requireNonNull(e.getRootCause())).getSQLState());
        }
        assertOrderOutbox(sagaId);
    }

    @Test
    void testDoublePaymentWithThreads(){
        String sagaId = UUID.randomUUID().toString();
        ExecutorService executor = null;

        try {
            executor = Executors.newFixedThreadPool(2);
            List<Callable<Object>> tasks = new ArrayList<>();

            tasks.add(Executors.callable(() -> {
                try {
                    paymentRequestMessageListener.completePayment(getPaymentRequest(sagaId));
                } catch (DataAccessException e) {
                    log.error("DataAccessException occurred for thread 1 with sql state: {}",
                            ((PSQLException) Objects.requireNonNull(e.getRootCause())).getSQLState());
                }
            }));
            tasks.add(Executors.callable(() -> {
                try {
                    paymentRequestMessageListener.completePayment(getPaymentRequest(sagaId));
                } catch (DataAccessException e) {
                    log.error("DataAccessException occurred for thread 2 with sql state: {}",
                            ((PSQLException) Objects.requireNonNull(e.getRootCause())).getSQLState());
                }
            }));

            executor.invokeAll(tasks);
            assertOrderOutbox(sagaId);
        } catch (InterruptedException e) {
            log.error("Error calling complete payment!");
        } finally {
            if (executor != null) {
                executor.shutdown();
            }
        }
    }

    private void assertOrderOutbox(String sagaId) {
        Optional<OrderOutboxEntity> orderOutboxEntity = orderOutboxJpaRepository.findByTypeAndSagaIdAndPaymentStatusAndOutboxStatus(ORDER_SAGA_NAME,
                UUID.fromString(sagaId),
                PaymentStatus.COMPLETED,
                OutboxStatus.STARTED);
        assertTrue(orderOutboxEntity.isPresent());
        assertEquals(orderOutboxEntity.get().getSagaId().toString(), sagaId);
    }

    private PaymentRequest getPaymentRequest(String sagaId) {
        return PaymentRequest.builder()
                .id(UUID.randomUUID().toString())
                .sagaId(sagaId)
                .orderId(UUID.randomUUID().toString())
                .paymentOrderStatus(PaymentOrderStatus.PENDING)
                .customerId(CUSTOMER_ID)
                .price(PRICE)
                .createdAt(Instant.now())
                .build();
    }

}
