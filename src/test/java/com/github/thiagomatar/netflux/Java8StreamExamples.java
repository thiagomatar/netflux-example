package com.github.thiagomatar.netflux;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Java8StreamExamples {

    @Test
    void simpleStreamExample() {
        List<String> phonetic = List.of("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");
        phonetic.stream().forEach(System.out::println);
    }

    @Test
    void parallelStreamExample() {
        List<String> phonetic = List.of("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");
        phonetic.parallelStream().forEach(System.out::println);
    }

    @Test
    void mapStreamExample() {
        List<String> phonetic = List.of("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");
        phonetic.stream().map(String::length)
                .forEach(System.out::println);
    }

    @Test
    void filterStreamExample() {
        List<String> phonetic = List.of("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");
        phonetic.stream().filter(f -> f.length() == 5)
                .forEach(System.out::println);
    }

    @Test
    void filterAndLimitStreamExample() {
        List<String> phonetic = List.of("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");
        phonetic.stream().filter(f -> f.length() == 5)
                .limit(1)
                .forEach(System.out::println);
    }

    @Test
    void filterAndSortStreamExample() {
        List<String> phonetic = List.of("Golf", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Alpha");
        phonetic.stream().filter(f -> f.length() == 5)
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    void filterAndSortStreamWithCollectorExample() {
        List<String> phonetic = List.of("Golf", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Alpha");
        String collect = phonetic.stream().filter(f -> f.length() == 5)
                .sorted()
                .collect(Collectors.joining(", "));

        System.out.println(collect);

    }

    @Test
    void filterAndSortStreamWithFunctionalComp() {
        List<String> phonetic = List.of("Golf", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Alpha");
        String collect = phonetic.stream()
                .filter(f -> f.length() == 5)
                .limit(2)
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.joining(", "));
        System.out.println(collect);
    }

    @Test
    void testStats() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        IntSummaryStatistics statistics = numbers.stream().mapToInt(x -> x)
                .summaryStatistics();
        System.out.println(statistics);
    }

    @Test
    void testMax() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        numbers.stream().mapToInt(x -> x)
                .max().ifPresent(System.out::println);
    }

    @Test
    void testMin() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        numbers.stream().mapToInt(x -> x)
                .min().ifPresent(System.out::println);
    }

    @Test
    void testSum() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int sum = numbers.stream().mapToInt(x -> x)
                .sum();
        System.out.println(sum);
    }

    @Test
    void testSumCollector() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int sum = numbers.stream().collect(Collectors.summingInt(s -> s));
        System.out.println(sum);
    }

    @Test
    void testGroupingBy() {
        List<String> phonetic = List.of("Golf", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Alpha");
        Map<Integer, Set<String>> collect = phonetic.stream().collect(Collectors.groupingBy(String::length, Collectors.toSet()));
        collect.entrySet().stream().forEach((System.out::println));
    }

    @Test
    void testFlatMap() {
        List<List<Integer>> listOfLists = List.of(List.of(1,2,3), List.of(4,5,6));
        listOfLists.stream().flatMap(Collection::stream)
                .forEach(System.out::println);
    }

    @Test
    void testReduction() {
        List<String> phonetic = List.of("Golf", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Alpha");
        phonetic.stream().reduce((a, b) -> a + "-" + b)
                .ifPresent(System.out::println);
    }
}
