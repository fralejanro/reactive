package com.learnreactiveprogramming.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FunctionalExample {


    public static void main(String[] args){
        var names = List.of("alex", "ben", "chloe", "adam");
        var newNamesList = namesGreaterThanSize(names, 3);
        System.out.println("newNamesList: "+ newNamesList);
    }

    private static List<String> namesGreaterThanSize(List<String> names, int size){
       return names.parallelStream()
                .filter(s->s.length()>size)
               .map(String::toUpperCase)
               .distinct()
               .sorted()
                .collect(Collectors.toList());
    }

}
