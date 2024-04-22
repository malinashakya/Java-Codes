/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication4;

/**
 *
 * @author malin
 */
public class IsArraySorted {

    public static boolean isSorted(int[] array) {
        int temp;
        Boolean mark = false;
        int[] array_temp = new int[array.length];
        array_temp = array;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i] > array[i]) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        for (int i = 0; i < array_temp.length; i++) {
            if (array[i] == array_temp[i]) {
                mark = true;
            }
            else{
            mark = false;
            }
        }
        return mark;
    }

    public static void main(String[] args) {
        int[] array1 = {1, 3, 5, 7};
        int[] array2 = {9, 4, 3};
        System.out.println("Array1"+isSorted(array1)); 
        System.out.println("Array2"+isSorted(array2));

    }
}
