package dev.hunghh.ordering.system.payment.service.dataaccess.outbox.adapter;

import dev.hunghh.ordering.system.domain.valueobject.PaymentStatus;
import dev.hunghh.ordering.system.outbox.OutboxStatus;
import dev.hunghh.ordering.system.payment.service.dataaccess.outbox.exception.OrderOutboxNotFoundException;
import dev.hunghh.ordering.system.payment.service.dataaccess.outbox.mapper.OrderOutDataboxAccessMapper;
import dev.hunghh.ordering.system.payment.service.dataaccess.outbox.repository.OrderOutboxJpaRepository;
import dev.hunghh.ordering.system.payment.service.domain.entity.Payment;
import dev.hunghh.ordering.system.payment.service.domain.outbox.model.OrderOutboxMessage;
import dev.hunghh.ordering.system.payment.service.domain.ports.output.repository.OrderOutboxRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderOutboxRepositoryImpl implements OrderOutboxRepository {

    private final OrderOutboxJpaRepository orderOutboxJpaRepository;
    private final OrderOutDataboxAccessMapper orderOutDataboxAccessMapper;

    public OrderOutboxRepositoryImpl(OrderOutboxJpaRepository orderOutboxJpaRepository,
                                     OrderOutDataboxAccessMapper orderOutDataboxAccessMapper) {
        this.orderOutboxJpaRepository = orderOutboxJpaRepository;
        this.orderOutDataboxAccessMapper = orderOutDataboxAccessMapper;
    }


    @Override
    public OrderOutboxMessage save(OrderOutboxMessage orderOutboxMessage) {
        return orderOutDataboxAccessMapper
                .orderOutboxEntityToOrderOutboxMessage(orderOutboxJpaRepository
                        .save(orderOutDataboxAccessMapper
                                .orderOutboxMessageToOrderOutboxEntity(orderOutboxMessage)));
    }

    @Override
    public Optional<List<OrderOutboxMessage>> findByTypeAndOutboxStatus(String type, OutboxStatus status) {
        return Optional.of(orderOutboxJpaRepository.findByTypeAndOutboxStatus(type, status)
                .orElseThrow(() -> new OrderOutboxNotFoundException("Approval outbox object cannot be found " +
                        "for saga type " + type))
                .stream()
                .map(orderOutDataboxAccessMapper::orderOutboxEntityToOrderOutboxMessage)
                .collect(Collectors.toList()));
    }

    @Override
    public Optional<OrderOutboxMessage> findByTpeAndSagaIdAndPaymentStatusAndOutboxStatus(String type, UUID sagaId, PaymentStatus paymentStatus, OutboxStatus outboxStatus) {
        return orderOutboxJpaRepository.findByTypeAndSagaIdAndPaymentStatusAndOutboxStatus(type, sagaId, paymentStatus, outboxStatus)
                .map(orderOutDataboxAccessMapper::orderOutboxEntityToOrderOutboxMessage);
    }

    @Override
    public void deleteByTypeAndOutboxStatus(String type, OutboxStatus status) {
        orderOutboxJpaRepository.deleteByTypeAndOutboxStatus(type, status);
    }
}
