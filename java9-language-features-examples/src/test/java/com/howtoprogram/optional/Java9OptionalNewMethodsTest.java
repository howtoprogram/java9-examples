package com.howtoprogram.optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Java9OptionalNewMethodsTest {

    @Test
    public void testOptionalIfPresentOrElse() {

        List<Optional<String>> days = List.of(Optional.of("Monday"),
                Optional.of("Tuesday"),
                Optional.empty(),
                Optional.ofNullable(null),
                Optional.of("Friday"),
                Optional.of("Saturday"),
                Optional.of("Sunday")
        );
        days.stream().forEach(p -> p.ifPresentOrElse(System.out::println,
                () -> System.out.println("Day not available")));
    }

    @Test
    public void testOptionalOr() {

        List<Optional<Integer>> studentAges = List.of(Optional.of(20),
                Optional.of(21),
                Optional.empty(),
                Optional.ofNullable(null),
                Optional.of(22),
                Optional.of(18),
                Optional.of(19)
        );
        studentAges.stream().map(p -> p.or(() -> Optional.of(20)))
                .forEach(System.out::println);
    }

    @Test
    public void testOptionalStream() {

        Optional<String> maleOpt = Optional.of("Male");
        Optional<String> feMaleOpt = Optional.of("FeMale");
        Optional<String> OtherOpt = Optional.empty();

        assertEquals("Male",maleOpt.stream().findFirst().get());
        assertTrue(maleOpt.stream().count() == 1);

        assertTrue(OtherOpt.stream().count() == 0);
    }

    @Test
    public void testImmutableListOrdered() {

        List<?> coffeeList = List.of("Espresso", "Latte", "Cappuccino");

        assertEquals("Espresso", coffeeList.get(0));

        assertEquals(2, coffeeList.indexOf("Cappuccino"));

    }

    @Test
    public void testImmutableListMutableElements() {

        Map<Integer, String> numbersMap = new HashMap<>();
        numbersMap.put(1, "One");
        numbersMap.put(2, "Two");

        Map<Integer, String> currencyMap = new HashMap<>();
        currencyMap.put(100, "Hundreds");
        currencyMap.put(1000, "Thousands");

        List<Map<Integer, String>> numCurrList = List.of(numbersMap, currencyMap);

        assertEquals(2, numCurrList.get(0).size());

        //Add more elements to the mutable element of the List
        numbersMap.put(3, "Three");
        numCurrList.get(0).put(4, "Four");

        assertEquals(4, numCurrList.get(0).size());

    }

    @Test
    public void testImmutableListSerializable() throws IOException {

        List<String> drinks = List.of("Coffee", "Wine", "Fruit");
        //serialize the list
        ObjectOutputStream oos = new ObjectOutputStream(System.out);
        oos.writeObject(drinks);

        //create a list of non-serializable objects
        List<OutputStream> outputStreamList = List.of(new ByteArrayOutputStream(),
                new PrintStream(System.out));

        assertThrows(NotSerializableException.class, () -> {

                    oos.writeObject(outputStreamList);
                }
        );
    }

}
