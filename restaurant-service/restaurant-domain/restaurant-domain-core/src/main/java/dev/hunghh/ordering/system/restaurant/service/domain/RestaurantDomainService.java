package dev.hunghh.ordering.system.restaurant.service.domain;

import dev.hunghh.ordering.system.domain.event.publisher.DomainEventPublisher;
import dev.hunghh.ordering.system.restaurant.service.domain.entity.Restaurant;
import dev.hunghh.ordering.system.restaurant.service.domain.event.OrderApprovalEvent;
import dev.hunghh.ordering.system.restaurant.service.domain.event.OrderApprovedEvent;
import dev.hunghh.ordering.system.restaurant.service.domain.event.OrderRejectedEvent;

import java.util.List;

public interface RestaurantDomainService {

    OrderApprovalEvent validateOrder(Restaurant restaurant,
                                     List<String> failureMessages);
}
