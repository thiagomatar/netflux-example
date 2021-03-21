package com.github.thiagomatar.netflux.controllers;

import com.github.thiagomatar.netflux.domain.Customer;
import com.github.thiagomatar.netflux.domain.CustomerEvent;
import com.github.thiagomatar.netflux.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<CustomerEvent> streamMovieEvent(@PathVariable("id") String id){
        return this.service.events(id);
    }


    @GetMapping("/{id}")
    Mono<Customer> getMovieById(@PathVariable("id") String id){
        return this.service.getCustomerById(id);
    }

    @GetMapping
    Flux<Customer> getAllMovies(){
        return this.service.getAllCustomers();
    }

}
