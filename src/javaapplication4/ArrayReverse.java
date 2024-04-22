package javaapplication4;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author malin
 */
public class ArrayReverse {

    public static void main(String[] args) {
        char[] array = {'H', 'E', 'L', 'L', 'O', ',', 'W', 'O', 'R', 'L', 'D'};
        int length = array.length;
        int length1 = length - 1;
        char temp;
        System.out.println("The array is:");
        for (int i = 0; i <= length1; i++) {
            System.out.println(array[i]);
        }
        System.out.println("Reverse array:");
        for (int i = 0; i < length/2+1; i++) {
            temp = array[i];
            array[i] = array[length1 - i];
            array[length1 - i] = temp;
//            System.out.println(array[i]);
        }
        for (int i = 0; i < length; i++) {
            System.out.println(array[i]);
        }
    }
}
