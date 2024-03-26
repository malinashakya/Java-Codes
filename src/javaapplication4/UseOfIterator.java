/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication4;

/**
 *
 * @author malin
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UseOfIterator {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Orange");

        // Get an Iterator for the list
        Iterator<String> iterator = list.iterator();

        // Iterate over the elements using hasNext() and next()
        System.out.println("Iterating through the list:");
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println(element);
        }
    }
}

    