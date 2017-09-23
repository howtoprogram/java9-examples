package com.howtoprogram.java9ex;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class HelloJava9 {

    public static void main(String args[]){

        List<String> fruits = List.of("Mangosteen", "Durian fruit", "Longan");

        System.out.println(fruits);

        Set<String> animals = Set.of("Cat", "Dog", "Pig");

        System.out.println(animals);
    }
}
