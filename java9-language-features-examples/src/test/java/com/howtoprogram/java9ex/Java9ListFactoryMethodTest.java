package com.howtoprogram.java9ex;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.*;

import org.junit.jupiter.api.Test;


public class Java9ListFactoryMethodTest {

    @Test
    public void testImmutableListNotModified() {


        List<String> cerealMix = List.of("Papaya", "Raisin", "Dried Apple", "Dried Peach");

        assertEquals(4, cerealMix.size());

        assertThrows(UnsupportedOperationException.class, () -> {
            // Add more element
            cerealMix.add("Wheat");
            // Remove element
            cerealMix.remove(1);
        });

    }

    @Test
    public void testImmutableListNullAttempts() {

        assertThrows(NullPointerException.class, () -> {
            List<String> stringList = List.of(null);
        });

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
