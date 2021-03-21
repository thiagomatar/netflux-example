package com.github.thiagomatar.netflux;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class ClosuresEffectivelyFinalAndLazyEval {

    @Test
    void lambdaExample() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        numbers.stream().map(number -> number * 2)
                .forEach(System.out::println);
    }

    @Test
    void closureExample() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Integer multiplier = 2;

        numbers.stream()
                .map(number -> number * multiplier)
                .forEach(System.out::println);
    }

    @Test
    void closureUsingFinal() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        final Integer multiplier = 2;

        numbers.stream()
                .map(number -> number * multiplier)
                .forEach(System.out::println);
    }

    @Test
    void breakingFinal(){
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        final Integer[] multiplier = {2};
        Stream<Integer> integerStream = numbers.stream().map(number -> number * multiplier[0]);
        multiplier[0] = 3;
        integerStream.forEach(System.out::println);

    }

}
