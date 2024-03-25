/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication4;

/**
 *
 * @author malin
 */
import java.util.HashMap;

public class hashMap_Example {

    public static void main(String[] args) {
        //Similar to HashSet but it references key with value, using key we can get value as well
        HashMap<String, Integer> VegetableWithPrice = new HashMap<>();
        VegetableWithPrice.put("Potato", 100);
        VegetableWithPrice.put("Tomato", 50);
        VegetableWithPrice.put("Cauliflower", 40);
        VegetableWithPrice.put("Brinjal", 20);
        System.out.println("Vegetable with Price:" + VegetableWithPrice);
        System.out.println("Price of Potato:" + VegetableWithPrice.get("Potato"));
        System.out.println("Price of Tomato:" + VegetableWithPrice.get("Tomato"));
        System.out.println("Price of Cauliflower:" + VegetableWithPrice.get("Cauliflower"));
        System.out.println("Price of Brinjal:" + VegetableWithPrice.get("Brinjal"));
        VegetableWithPrice.put("Cauliflower", 80);
        VegetableWithPrice.put("Brinjal", 20);
        VegetableWithPrice.put("Lemon", 30);
        System.out.println("All vegetables with price:" + VegetableWithPrice);

    }
}
