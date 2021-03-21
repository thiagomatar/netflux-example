package com.github.thiagomatar.netflux.service;

import com.github.thiagomatar.netflux.domain.Customer;
import com.github.thiagomatar.netflux.domain.CustomerEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Flux<CustomerEvent> events(String customerId);

    Mono<Customer> getCustomerById(String id);

    Flux<Customer> getAllCustomers();

}
