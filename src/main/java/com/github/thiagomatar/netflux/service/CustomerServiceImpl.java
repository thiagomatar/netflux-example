package com.github.thiagomatar.netflux.service;

import com.github.thiagomatar.netflux.domain.Customer;
import com.github.thiagomatar.netflux.domain.CustomerEvent;
import com.github.thiagomatar.netflux.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;


    @Override
    public Flux<CustomerEvent> events(String customerId) {
        return Flux.<CustomerEvent>generate(customerEventSynchronousSink -> {
            customerEventSynchronousSink.next(new CustomerEvent(customerId, LocalDate.now()));
        }).delayElements(Duration.ofSeconds(1));
    }

    @Override
    public Mono<Customer> getCustomerById(String id) {
        return this.repository.findById(id);
    }

    @Override
    public Flux<Customer> getAllCustomers() {
        return this.repository.findAll();
    }
}
