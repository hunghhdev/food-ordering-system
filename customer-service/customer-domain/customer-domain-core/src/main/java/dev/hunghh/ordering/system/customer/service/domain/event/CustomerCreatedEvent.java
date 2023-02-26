package dev.hunghh.ordering.system.customer.service.domain.event;


import dev.hunghh.ordering.system.customer.service.domain.entity.Customer;
import dev.hunghh.ordering.system.domain.event.DomainEvent;

import java.time.ZonedDateTime;

public class CustomerCreatedEvent implements DomainEvent<Customer> {

    private final Customer customer;
    private final ZonedDateTime createdAt;

    public CustomerCreatedEvent(Customer customer, ZonedDateTime createdAt) {
        this.customer = customer;
        this.createdAt = createdAt;
    }

    public Customer getCustomer() {
        return customer;
    }
}