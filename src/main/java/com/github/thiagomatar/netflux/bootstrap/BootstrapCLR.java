package com.github.thiagomatar.netflux.bootstrap;

import com.github.thiagomatar.netflux.domain.Customer;
import com.github.thiagomatar.netflux.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BootstrapCLR implements CommandLineRunner {

    private final CustomerRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll().thenMany(
                Flux.just("Thiago", "Thamara", "Alexandre", "Jade", "Sophie", "Baby"
                        , "Dora", "Sininho", "Pepeu", "Olaf", "Kiwi")
                        .map(name -> new Customer(UUID.randomUUID().toString(), name))
                        .flatMap(repository::save))
                .subscribe(null, null, () -> {
                    repository.findAll().subscribe(System.out::println);
                });

    }
}
