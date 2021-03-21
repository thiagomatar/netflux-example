package com.github.thiagomatar.netflux;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class FunctionalProgrammingExamples {

    /**
     * Propertoes of a function
     * 1. name
     * 2. return type
     * 3. parameter list
     * 4. body
     */

    @Test
    void functionWith4Things(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() { //method
                System.out.println("In a thread t1"); //body
            }
        });
        t1.start();
        System.out.println("In main Test");
    }

    @Test
    void lambdaExpression(){
            Thread t1 = new Thread(()-> System.out.println("Silence of lambdas"));
            t1.start();
        System.out.println("In main test");
    }

    @Test
    void listIteratorHighCeremony(){
        List<String> phonetic = List.of("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");
        for (int i = 0; i < phonetic.size(); i++){
            System.out.println(phonetic.get(i));
        }
    }

    @Test
    void listIteratorLessCeremonyExternalIterator(){
        List<String> phonetic = List.of("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");
        for (String name: phonetic) {
            System.out.println(name);
        }
    }

    @Test
    void listInternalIteratorConsumer(){
        List<String> phonetic = List.of("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");
        phonetic.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    void listInternalIteratorLambdaMethod(){
        List<String> phonetic = List.of("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");
        phonetic.forEach(s-> System.out.println(s));
    }

    @Test
    void listInternalIteratorLambdaMethodTypeMethodReference(){
        List<String> phonetic = List.of("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");
        phonetic.forEach(System.out::println);
    }

    @Test
    void countNamesWithFiveCharacters(){
        List<String> phonetic = List.of("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");

        int nameCount = 0;
        for (String name: phonetic) {
            if(name.length() == 5){
                nameCount++;
            }
        }
        System.out.println(nameCount);
    }

    @Test
    void countNameWithFiveCharactersDec(){
        List<String> phonetic = List.of("Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf");
        int count = (int) phonetic.stream().filter(f -> f.length() == 5).count();
        System.out.println(count);
    }
}
