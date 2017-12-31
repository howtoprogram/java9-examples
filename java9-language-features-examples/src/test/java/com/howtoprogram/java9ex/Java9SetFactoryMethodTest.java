package com.howtoprogram.java9ex;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class Java9SetFactoryMethodTest {


    @Test
    public void testImmutableSetJava9() {

        Set<String> japanNoodles = Set.of("Ramen", "Yakisoba", "Udon", "Soba", "Somen");
        assertEquals(5, japanNoodles.size());
    }

    @Test
    public void testImmutableSetNotModified() {


        Set<String> japanNoodles = Set.of("Ramen", "Yakisoba", "Udon", "Soba", "Somen");

        assertEquals(5, japanNoodles.size());

        assertThrows(UnsupportedOperationException.class, () -> {
            // Add more element
            japanNoodles.add("Negi");
            // Remove element
            japanNoodles.remove(1);
        });

    }

    @Test
    public void testImmutableSetNullAttempts() {

        assertThrows(NullPointerException.class, () -> {
            Set<String> stringList = Set.of(null);
        });

    }

    @Test
    public void testImmutableSetRejectDupplicates() {

        assertThrows(IllegalArgumentException.class, () -> {
            Set<?> teaSet = Set.of("Shincha", "Aki Bancha", "Hojicha", "Hojicha");
        });

    }

    @Test
    public void testImmutableSetMutableElements() {

        Map<Integer, String> noodleToppingsMap = new HashMap<>();
        noodleToppingsMap.put(1, "Negi");
        noodleToppingsMap.put(2, "Tamago");
        noodleToppingsMap.put(3, "Nori");

        Map<Integer, String> japanTeaMap = new HashMap<>();
        japanTeaMap.put(1, "Ryokucha");
        japanTeaMap.put(2, "Yamecha");
        japanTeaMap.put(3, "Ujicha");

        List<Map<Integer, String>> choices = List.of(noodleToppingsMap, japanTeaMap);

        assertEquals(3, choices.get(0).size());

        //Add more elements to the mutable element of the List
        noodleToppingsMap.put(3, "Tsuyu");
        choices.get(0).put(4, "Aracha");

        assertEquals(4, choices.get(0).size());

    }

    @Test
    public void testImmutableSetSerializable() throws IOException {

        Set<String> drinks = Set.of("Gyokuro", "Tamaryokucha", "Kamairicha");
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
