package dev.hunghh.ordering.system.customer.service;

import dev.hunghh.ordering.system.customer.service.domain.CustomerDomainService;
import dev.hunghh.ordering.system.customer.service.domain.CustomerDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CustomerDomainService customerDomainService() {
        return new CustomerDomainServiceImpl();
    };
}
