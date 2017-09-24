package com.howtoprogram.java9ex;
import java.util.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Java9NewFeaturesTest {

    @Test
    public void testImmutableListNotModified() {


        List<String> teas = List.of("Green Tea", "Black Tea", "White Tea");

        assertEquals(3, teas.size());

      

    }

}
