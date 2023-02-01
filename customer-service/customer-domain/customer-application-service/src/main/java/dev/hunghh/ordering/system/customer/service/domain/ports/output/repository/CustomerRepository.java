package dev.hunghh.ordering.system.customer.service.domain.ports.output.repository;

import dev.hunghh.ordering.system.customer.service.domain.entity.Customer;

public interface CustomerRepository {
    Customer createCustomer(Customer customer);
}
