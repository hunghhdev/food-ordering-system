package dev.hunghh.ordering.system.order.service.dataaccess.restaurant.mapper;

import dev.hunghh.ordering.system.dataaccess.restaurant.entity.RestaurantEntity;
import dev.hunghh.ordering.system.dataaccess.restaurant.exception.RestaurantDataAccessException;
import dev.hunghh.ordering.system.domain.valueobject.Money;
import dev.hunghh.ordering.system.domain.valueobject.ProductId;
import dev.hunghh.ordering.system.domain.valueobject.RestaurantId;
import dev.hunghh.ordering.system.order.service.domain.entity.Product;
import dev.hunghh.ordering.system.order.service.domain.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantDataAccessMapper {

    public List<UUID> restaurantToRestaurantProducts(Restaurant restaurant) {
        return restaurant.getProducts().stream()
                .map(product -> product.getId().getValue())
                .collect(Collectors.toList());
    }

    public Restaurant restaurantEntityToRestaurant(List<RestaurantEntity> restaurantEntities) {
        RestaurantEntity restaurantEntity =
                restaurantEntities.stream().findFirst().orElseThrow(() ->
                        new RestaurantDataAccessException("Restaurant could not be found!"));
        List<Product> restaurantProducts = restaurantEntities.stream()
                .map(entity -> new Product(new ProductId(entity.getProductId()), entity.getProductName(),
                        new Money(entity.getProductPrice())))
                .collect(Collectors.toList());
        return Restaurant.builder()
                .restaurantId(new RestaurantId(restaurantEntity.getRestaurantId()))
                .products(restaurantProducts)
                .active(restaurantEntity.getRestaurantActive())
                .build();
    }
}
