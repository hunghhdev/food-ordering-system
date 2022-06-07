package dev.hunghh.ordering.system.restaurant.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"dev.hunghh.ordering.system.restaurant.service.dataaccess", "dev.hunghh.ordering.system.dataaccess"})
@EntityScan(basePackages = {"dev.hunghh.ordering.system.restaurant.service.dataaccess", "dev.hunghh.ordering.system.dataaccess"})
@SpringBootApplication(scanBasePackages = "dev.hunghh.ordering.system")
public class RestaurantServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantServiceApplication.class, args);
    }
}
