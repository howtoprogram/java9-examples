package com.howtoprogram.java9ex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.List;

public class Java9NewFeaturesTest {


    @Test
    public void testImmutableCollections() {

        List<String> fruits = List.of("Mangosteen", "Durian fruit", "Longan");

        assertThrows(UnsupportedOperationException.class, () -> {
            fruits.add("Mango");
            fruits.remove(1);
        });

        assertEquals(3, fruits.size());

    }


}
