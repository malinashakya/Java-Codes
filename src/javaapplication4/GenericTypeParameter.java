/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication4;

/**
 *
 * @author malin
 */
public class GenericTypeParameter<T> {

    public Object[] array;
    public int size;

    public GenericTypeParameter() {
        array = new Object[25];
        size = 0;
    }

    public boolean add(T value) {
        if (contains(value)) {
            return false;
        }

        int index = getIndex(value);

        array[index] = value;
        size++;
        return true;
    }

    public int size() {
        return size;
    }

    public int getIndex(Object obj) {
        int index = Math.abs(obj.hashCode() % 25);

        return index;
    }

    public boolean contains(Object value) {
        int index = getIndex(value);
        Object previousValue = array[index];
        return (previousValue != null && previousValue.equals(value));

    }

    public static void main(String[] args) {
        //Instead of Integer we can put String as well but will accept String type only
        GenericTypeParameter<Integer> set = new GenericTypeParameter<Integer>();
        //Can not take other parameter than Integer
//        set.add("Apple"); Error
        set.add(11);
        System.out.println(set.add(11));
        set.add(46);
        for (Object element : set.array) {
            if (element != null) {
                System.out.println("Element: " + element);
            }
        }

        System.out.println("Size: " + set.size());
    }
}
