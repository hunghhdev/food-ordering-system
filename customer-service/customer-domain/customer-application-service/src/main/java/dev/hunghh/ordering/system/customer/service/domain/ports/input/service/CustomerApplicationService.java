package dev.hunghh.ordering.system.customer.service.domain.ports.input.service;

import dev.hunghh.ordering.system.customer.service.domain.create.CreateCustomerCommand;
import dev.hunghh.ordering.system.customer.service.domain.create.CreateCustomerResponse;

import javax.validation.Valid;

public interface CustomerApplicationService {

    CreateCustomerResponse createCustomer(@Valid CreateCustomerCommand createCustomerCommand);

}
