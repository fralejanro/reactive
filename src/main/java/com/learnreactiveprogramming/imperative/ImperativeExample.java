package com.learnreactiveprogramming.imperative;

import java.util.ArrayList;
import java.util.List;

public class ImperativeExample {

    public static void main(String[] args){
        var names = List.of("alex", "ben", "chloe", "adam", "adam");
        var newNamesList = namesGreaterThanSize(names, 3);
        System.out.println("newNamesList: "+ newNamesList);
    }

    private static List<String> namesGreaterThanSize(List<String> names, int size){
        var newNamesList = new ArrayList<String>();
        for(String name: names){
            if(name.length()>size && !newNamesList.contains(name)){
                newNamesList.add(name.toUpperCase());
            }
        }
        return newNamesList;
    }
}
