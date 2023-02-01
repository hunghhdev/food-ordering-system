package dev.hunghh.ordering.system.customer.service.domain.ports.output.message.publisher;

import dev.hunghh.ordering.system.customer.service.domain.event.CustomerCreatedEvent;

public interface CustomerMessagePublisher {
    void publish(CustomerCreatedEvent customerCreatedEvent);
}
