package dev.hunghh.ordering.system.customer.service.domain;

import dev.hunghh.ordering.system.customer.service.domain.entity.Customer;
import dev.hunghh.ordering.system.customer.service.domain.event.CustomerCreatedEvent;

public interface CustomerDomainService {
    CustomerCreatedEvent validateAndInitiateCustomer(Customer customer);
}
