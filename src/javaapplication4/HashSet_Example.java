/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication4;

/**
 *
 * @author malin
 */
import java.util.HashSet;
public class HashSet_Example {
    public static void main(String[] args) {
        //HashSet ma chai duplicate entry hunna
        HashSet<Integer> numbers=new HashSet<>();
        numbers.add(10);
        numbers.add(3);
        numbers.add(9);
        System.out.println("Numbers in Hashset:"+numbers);
        numbers.add(4);
        numbers.add(10);
        System.out.println("Numbers in Hashset after again adding 4 and 10:"+numbers);
        
        HashSet <String> fruits=new HashSet<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Mango");
        fruits.add("Papaya");
        System.out.println("Fruits inside fruits:"+fruits);
        fruits.add("Watermelon");
        fruits.add("Strawberry");
        fruits.add("Mango");
        System.out.println("Fruits inside fruits after adding Watermelon, Strawberry, Mango (again)"+fruits);
        
    }
}
