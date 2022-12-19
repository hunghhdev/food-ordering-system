package dev.hunghh.ordering.system.order.service.dataaccess.outbox.restaurantapproval.repository;

import dev.hunghh.ordering.system.order.service.dataaccess.outbox.restaurantapproval.entity.ApprovalOutboxEntity;
import dev.hunghh.ordering.system.outbox.OutboxStatus;
import dev.hunghh.ordering.system.saga.SagaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApprovalOutboxJpaRepository extends JpaRepository<ApprovalOutboxEntity, UUID> {

    Optional<List<ApprovalOutboxEntity>> findByTypeAndOutboxStatusAndSagaStatusIn(String type,
                                                                                  OutboxStatus outboxStatus,
                                                                                  List<SagaStatus> sagaStatus);

    Optional<ApprovalOutboxEntity> findByTypeAndSagaIdAndSagaStatusIn(String type,
                                                                      UUID sagaId,
                                                                      List<SagaStatus> sagaStatus);

    void deleteByTypeAndOutboxStatusAndSagaStatusIn(String type,
                                                    OutboxStatus outboxStatus,
                                                    List<SagaStatus> sagaStatus);

}
