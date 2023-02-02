package dev.hunghh.ordering.system.order.service.domain.ports.output.message.publisher.customer;

import dev.hunghh.ordering.system.order.service.domain.dto.message.CustomerModel;

public interface CustomerMessageListener {

    void customerCreated(CustomerModel customerModel);
}
