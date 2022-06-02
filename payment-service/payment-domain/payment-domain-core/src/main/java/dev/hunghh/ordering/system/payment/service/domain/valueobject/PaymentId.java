package dev.hunghh.ordering.system.payment.service.domain.valueobject;

import dev.hunghh.ordering.system.domain.valueobject.BaseId;

import java.util.UUID;

public class PaymentId extends BaseId<UUID> {
    public PaymentId(UUID value) {
        super(value);
    }
}
