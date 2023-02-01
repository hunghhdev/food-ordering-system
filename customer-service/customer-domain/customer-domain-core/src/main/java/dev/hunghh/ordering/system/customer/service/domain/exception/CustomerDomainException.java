package dev.hunghh.ordering.system.customer.service.domain.exception;

import dev.hunghh.ordering.system.domain.exception.DomainException;

public class CustomerDomainException extends DomainException {
    public CustomerDomainException(String message) {
        super(message);
    }
}
