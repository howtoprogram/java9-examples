package com.howtoprogram.java9ex;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Java9ArrayCompareTest {

    @Test
    public void arrayEqualsTest() {

        int[] existRows = {0, 1, 2, 3, 4, 5};
        int[] newRows = {3, 4, 5, 1, 2, 0};

        assertFalse(Arrays.equals(existRows, newRows));

        assertTrue(Arrays.equals(existRows, 1, 3, newRows, 3, 5));
        assertTrue(Arrays.equals(existRows, 3, 5, newRows, 0, 2));

    }

    @Test
    public void compareArraysTest() {


        int[] tomMarks = {5, 6, 7, 8, 9, 10};

        int[] aliceMarks = {5, 6, 7, 8, 9, 10};

        int[] daisyMarks = {5, 6, 7, 10, 9, 10};

        int[] maryMarks = {5, 6, 7, 8};


        assertEquals(0, Arrays.compare(tomMarks, aliceMarks));

        assertEquals(-1, Arrays.compare(tomMarks, daisyMarks));

        assertEquals(2, Arrays.compare(tomMarks, maryMarks));
    }

    @Test
    public void compareSliceArraysTest() {


        int[] tomMarks = {5, 6, 7, 8, 9, 10};

        int[] daisyMarks = {5, 6, 7, 10, 9, 10};

        int[] maryMarks = {5, 6, 7, 8};


        assertEquals(0, Arrays.compare(tomMarks, 0, 3,
                daisyMarks, 0, 3));

        assertEquals(0, Arrays.compare(tomMarks, 0, 4,
                maryMarks, 0, maryMarks.length));

        assertEquals(1, Arrays.compare(daisyMarks, 0, 4,
                maryMarks, 0, maryMarks.length));
    }


    @Test
    public void mismatchArraysTest() {


        int[] a = {1, 2, 3, 4, 5};
        int[] b = {1, 2, 3, 4, 5};
        int[] c = {1, 2, 4, 4, 5, 6};

        assertEquals(-1, Arrays.mismatch(a, b));

        assertEquals(2, Arrays.mismatch(a, c));

        assertEquals(-1, Arrays.mismatch(a, 0, 2, c, 0, 2));

        assertEquals(2, Arrays.mismatch(a, 0, 3, c, 0, 3));

        //mismatch return relative index
        assertEquals(0, Arrays.mismatch(a, 2, a.length, c, 2, 5));
    }
}
