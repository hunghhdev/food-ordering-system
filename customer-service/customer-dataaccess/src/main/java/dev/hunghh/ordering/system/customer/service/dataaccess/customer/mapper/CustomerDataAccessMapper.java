package dev.hunghh.ordering.system.customer.service.dataaccess.customer.mapper;

import dev.hunghh.ordering.system.customer.service.dataaccess.customer.entity.CustomerEntity;
import dev.hunghh.ordering.system.customer.service.domain.entity.Customer;
import dev.hunghh.ordering.system.domain.valueobject.CustomerId;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {

    public Customer customerEntityToCustomer(CustomerEntity entity) {
        return new Customer(new CustomerId(entity.getId()),
                entity.getUsername(),
                entity.getFirstName(),
                entity.getLastName());
    }

    public CustomerEntity customerToCustomerEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId().getValue())
                .username(customer.getUsername())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
    }
}
