package dev.hunghh.ordering.system.order.service.domain.valueobject;

import dev.hunghh.ordering.system.domain.valueobject.BaseId;

public class OrderItemId extends BaseId<Long> {
    public OrderItemId(Long value) {
        super(value);
    }
}
