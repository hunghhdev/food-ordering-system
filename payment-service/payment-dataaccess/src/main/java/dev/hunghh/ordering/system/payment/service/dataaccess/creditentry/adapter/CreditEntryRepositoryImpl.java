package dev.hunghh.ordering.system.payment.service.dataaccess.creditentry.adapter;

import dev.hunghh.ordering.system.domain.valueobject.CustomerId;
import dev.hunghh.ordering.system.payment.service.dataaccess.creditentry.mapper.CreditEntryDataAccessMapper;
import dev.hunghh.ordering.system.payment.service.dataaccess.creditentry.repository.CreditEntryJpaRepository;
import dev.hunghh.ordering.system.payment.service.domain.entity.CreditEntry;
import dev.hunghh.ordering.system.payment.service.domain.entity.Payment;
import dev.hunghh.ordering.system.payment.service.domain.ports.output.repository.CreditEntryRepository;
import dev.hunghh.ordering.system.payment.service.domain.ports.output.repository.PaymentRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CreditEntryRepositoryImpl implements CreditEntryRepository {

    private final CreditEntryJpaRepository creditEntryJpaRepository;
    private final CreditEntryDataAccessMapper creditEntryDataAccessMapper;

    public CreditEntryRepositoryImpl(CreditEntryJpaRepository creditEntryJpaRepository,
                                     CreditEntryDataAccessMapper creditEntryDataAccessMapper) {
        this.creditEntryJpaRepository = creditEntryJpaRepository;
        this.creditEntryDataAccessMapper = creditEntryDataAccessMapper;
    }

    @Override
    public CreditEntry save(CreditEntry creditEntry) {
        return creditEntryDataAccessMapper
                .creditEntryEntityToCreditEntry(creditEntryJpaRepository
                        .save(creditEntryDataAccessMapper.creditEntryToCreditEntryEntity(creditEntry)));
    }

    @Override
    public Optional<CreditEntry> findByCustomerId(CustomerId customerId) {
        return creditEntryJpaRepository
                .findByCustomerId(customerId.getValue())
                .map(creditEntryDataAccessMapper::creditEntryEntityToCreditEntry);
    }
}
