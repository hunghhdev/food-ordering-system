package dev.hunghh.ordering.system.restaurant.service.domain.valueobject;

import dev.hunghh.ordering.system.domain.valueobject.BaseId;

import java.util.UUID;

public class OrderApprovalId extends BaseId<UUID> {

    public OrderApprovalId(UUID value) {
        super(value);
    }
}
