package com.howtoprogram.java9ex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Java9NewStreamAPITest {

    @Test
    public void dropWhileTest() {

        Stream<Integer> stdMarks = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Stream<Integer> goodMarks = stdMarks.dropWhile(s -> s < 5);

        goodMarks.forEach(System.out::println);
        System.out.println("--------------------------------");

        Stream<Integer> givenMarks = Stream.of(1, 2, 10, 9, 5, 6, 2, 3);
        Stream<Integer> remainMarks = givenMarks.dropWhile(s -> s < 5);

        remainMarks.forEach(System.out::println);
    }

    @Test
    public void takeWhileTest() {

        Stream<Integer> stdMarks = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Stream<Integer> ngMarks = stdMarks.takeWhile(s -> s < 5);

        ngMarks.forEach(System.out::println);
        System.out.println("--------------------------------");

        Stream<Integer> givenMarks = Stream.of(1, 2, 10, 9, 5, 6, 2, 3);
        Stream<Integer> remainMarks = givenMarks.takeWhile(s -> s < 5);

        remainMarks.forEach(System.out::println);
    }

    @Test
    public void streamTest() {
        Stream.iterate(0, n -> n + 1)
                .forEach(System.out::println);
        System.out.println("--------------------------------");
        Stream.iterate(0, n -> n < 100, n -> n + 1)
                .forEach(System.out::println);
    }

    @Test
    public void ofNullableTest() {

        String username = null;
        Stream st = Stream.ofNullable(username);

        assertEquals(0, st.count());

        username ="admin";
        st = Stream.ofNullable(username);

        assertEquals(1, st.count());

    }

}
