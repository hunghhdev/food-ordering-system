package dev.hunghh.ordering.system.payment.service.dataaccess.credithistory.adapter;

import dev.hunghh.ordering.system.domain.valueobject.CustomerId;
import dev.hunghh.ordering.system.payment.service.dataaccess.credithistory.entity.CreditHistoryEntity;
import dev.hunghh.ordering.system.payment.service.dataaccess.credithistory.mapper.CreditHistoryDataAccessMapper;
import dev.hunghh.ordering.system.payment.service.dataaccess.credithistory.repository.CreditHistoryJpaRepository;
import dev.hunghh.ordering.system.payment.service.domain.entity.CreditEntry;
import dev.hunghh.ordering.system.payment.service.domain.entity.CreditHistory;
import dev.hunghh.ordering.system.payment.service.domain.ports.output.repository.CreditEntryRepository;
import dev.hunghh.ordering.system.payment.service.domain.ports.output.repository.CreditHistoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CreditEntryRepositoryImpl implements CreditHistoryRepository {

    private final CreditHistoryJpaRepository creditHistoryJpaRepository;
    private final CreditHistoryDataAccessMapper creditHistoryDataAccessMapper;

    public CreditEntryRepositoryImpl(CreditHistoryJpaRepository creditHistoryJpaRepository,
                                     CreditHistoryDataAccessMapper creditHistoryDataAccessMapper) {
        this.creditHistoryJpaRepository = creditHistoryJpaRepository;
        this.creditHistoryDataAccessMapper = creditHistoryDataAccessMapper;
    }

    @Override
    public CreditHistory save(CreditHistory creditHistory) {
        return creditHistoryDataAccessMapper
                .creditHistoryEntityToCreditHistory(creditHistoryJpaRepository
                        .save(creditHistoryDataAccessMapper.creditHistoryToCreditHistoryEntity(creditHistory)));
    }

    @Override
    public Optional<List<CreditHistory>> findByCustomerId(CustomerId customerId) {
        Optional<List<CreditHistoryEntity>> creditHistory = creditHistoryJpaRepository.findByCustomerId(customerId.getValue());
        return creditHistory
                .map(creditHistoryEntities ->
                        creditHistoryEntities.stream()
                                .map(creditHistoryDataAccessMapper::creditHistoryEntityToCreditHistory)
                                .collect(Collectors.toList()));
    }
}
