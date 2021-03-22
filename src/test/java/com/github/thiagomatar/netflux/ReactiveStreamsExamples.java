package com.github.thiagomatar.netflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ReactiveStreamsExamples {

    @Test
    void simpleStreamExample() {
        Flux<String> phonetic = Flux.just("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");

        phonetic.toStream().forEach(System.out::println);
    }

    @Test
    void simpleStreamExample2() {
        Flux<String> phonetic = Flux.just("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");
        phonetic.subscribe(System.out::println);
    }

    @Test
    void simpleStreamExample3() {
        Flux<String> phonetic = Flux.just("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");
        phonetic.doOnEach(name -> System.out.println(name.get()));
    }

    @Test
    void simpleStreamExample4() {
        Flux<String> phonetic = Flux.just("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");
        phonetic.doOnEach(name -> System.out.println(name.get())).subscribe();
    }

    @Test
    void simpleStreamExample5WithSubscriber() {
        Flux<String> phonetic = Flux.just("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");
        phonetic.subscribe((s) -> System.out.println(s), null, () -> System.out.println("All done"));
    }

    @Test
    void simpleStreamExample5WithSubscribeConsumers() {
        Flux<String> phonetic = Flux.just("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");
        Consumer<String> println = System.out::println;
        Consumer<Throwable> errorHandler = e -> System.out.println("Some error occurred");
        Runnable allDone = () -> System.out.println("All done");
        phonetic.subscribe(println, errorHandler, allDone);
    }

    //
    @Test
    void mapStreamExample() {
        Flux<String> phonetic = Flux.just("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");
        phonetic.map(String::length)
                .doOnEach(System.out::println)
                .subscribe();
    }

    @Test
    void filterStreamExample() {
        Flux<String> phonetic = Flux.just("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");
        phonetic.filter(s -> s.length() == 5)
                .subscribe(System.out::println);
    }

    @Test
    void filterAndLimitStreamExample() {
        Flux<String> phonetic = Flux.just("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");
        phonetic.filter(s -> s.length() == 5)
                .take(2)
                .subscribe(System.out::println);
    }

    @Test
    void filterAndSortStreamExample() {
        Flux<String> phonetic = Flux.just("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");
        phonetic.filter(s -> s.length() == 5)
                .take(2)
                .sort()
                .subscribe(System.out::println);
    }

    @Test
    void filterAndSortStreamWithCollectorExample() {
        Flux<String> phonetic = Flux.just("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");
        phonetic.filter(s -> s.length() == 5)
                .take(2)
                .sort()
                .collect(Collectors.joining(", "))
                .subscribe(System.out::println);
    }

    @Test
    void testFlatMap() {
        Flux<List<List<Integer>>> listFlux = Flux.just(List.of(List.of(1, 2, 3), List.of(4, 5, 6)));
        listFlux.flatMap(lists -> Flux.fromIterable(lists))
                .flatMap(list -> Flux.fromIterable(list))
                .subscribe(System.out::println);
    }

    @Test
    void testFlapMap2() {
        Flux<List<List<Integer>>> listFlux = Flux.just(List.of(List.of(1, 2, 3), List.of(4, 5, 6)));
        listFlux.flatMap(list -> Flux.fromIterable(list
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList())))
                .subscribe(System.out::println);
    }

    @Test
    void testReduction() {
        Flux<String> phonetic = Flux.just("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");
        phonetic.reduce((a, b) -> a + "-" + b).subscribe(System.out::println);
    }
}
