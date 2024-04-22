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
    for(int i=0;i<array.length-1;i++)
    {
        if(array[i]>array[i+1])
        {
            return false;
        }
        
    }
     return true;
    }

    public static void main(String[] args) {
        int[] array1 = {1, 3, 5, 7};
        int[] array2 = {9, 4, 3};
        System.out.println("Array1 is sorted?"+isSorted(array1)); 
        System.out.println("Array2 is sorted?"+isSorted(array2));

    }
}
