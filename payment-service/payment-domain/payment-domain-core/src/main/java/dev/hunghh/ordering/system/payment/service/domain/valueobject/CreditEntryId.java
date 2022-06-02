package dev.hunghh.ordering.system.payment.service.domain.valueobject;

import dev.hunghh.ordering.system.domain.valueobject.BaseId;

import java.util.UUID;

public class CreditEntryId extends BaseId<UUID> {
    public CreditEntryId(UUID value) {
        super(value);
    }
}
