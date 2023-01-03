package dev.hunghh.ordering.system.payment.service.messaging.publisher.kafka;

import dev.hunghh.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel;
import dev.hunghh.ordering.system.kafka.producer.KafkaMessageHelper;
import dev.hunghh.ordering.system.kafka.producer.service.KafkaProducer;
import dev.hunghh.ordering.system.outbox.OutboxStatus;
import dev.hunghh.ordering.system.payment.service.domain.config.PaymentServiceConfigData;
import dev.hunghh.ordering.system.payment.service.domain.outbox.model.OrderEventPayload;
import dev.hunghh.ordering.system.payment.service.domain.outbox.model.OrderOutboxMessage;
import dev.hunghh.ordering.system.payment.service.domain.ports.output.message.publisher.PaymentResponseMessagePublisher;
import dev.hunghh.ordering.system.payment.service.messaging.mapper.PaymentMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Slf4j
@Component
public class PaymentEventKafkaPublisher implements PaymentResponseMessagePublisher {

    private final PaymentMessagingDataMapper paymentMessagingDataMapper;
    private final KafkaProducer<String, PaymentResponseAvroModel> kafkaProducer;
    private final PaymentServiceConfigData paymentServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;

    public PaymentEventKafkaPublisher(PaymentMessagingDataMapper paymentMessagingDataMapper,
                                      KafkaProducer<String, PaymentResponseAvroModel> kafkaProducer,
                                      PaymentServiceConfigData paymentServiceConfigData,
                                      KafkaMessageHelper kafkaMessageHelper) {
        this.paymentMessagingDataMapper = paymentMessagingDataMapper;
        this.kafkaProducer = kafkaProducer;
        this.paymentServiceConfigData = paymentServiceConfigData;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    public void publisher(OrderOutboxMessage orderOutboxMessage, BiConsumer<OrderOutboxMessage, OutboxStatus> outboxCallback) {
        OrderEventPayload orderEventPayload
                = kafkaMessageHelper.getOrderEventPayload(orderOutboxMessage.getPayload(), OrderEventPayload.class);

        String sagaId = orderOutboxMessage.getSagaId().toString();

        log.info("Received OrderOutboxMessage for order id: {} and saga id: {}",
                orderEventPayload.getOrderId(),
                sagaId);

        PaymentResponseAvroModel paymentResponseAvroModel = null;
        try {
            paymentResponseAvroModel = paymentMessagingDataMapper
                    .orderEventPayloadToPaymentResponseAvroModel(sagaId, orderEventPayload);
            kafkaProducer.send(paymentServiceConfigData.getPaymentResponseTopicName(),
                    sagaId,
                    paymentResponseAvroModel,
                    kafkaMessageHelper.getKafkaCallBack(paymentServiceConfigData.getPaymentResponseTopicName(),
                            paymentResponseAvroModel,
                            orderOutboxMessage,
                            outboxCallback,
                            orderEventPayload.getOrderId(),
                            "PaymentResponseAvroModel"));

            log.info("PaymentResponseAvroModel sent to kafka for order id: {} and saga id: {}",
                    paymentResponseAvroModel.getOrderId(), sagaId);
        } catch (Exception e) {
            log.error("Error while sending PaymentResponseAvroModel message to kafka with order id: {} and saga id: {}, error: {}",
                    orderEventPayload.getOrderId(), sagaId, e.getMessage());
        }
    }
}
