/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication4;

import java.util.Iterator;

/**
 *
 * @author malin
 */
public class NewArrayList implements Iterable<Object> {

    int capacity = 5;
    Object[] array;
    int size;

    public NewArrayList() {
        array = new Object[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public void add(Object element) {
        if (size == array.length) {
            capacityIncreased();
        }
        array[size++] = element;
    }

    public void add(int index, Object element) {
        if (index < 0 || index > size) {
            throw new Error("Index is not present:" + index);
        }
        if (size == array.length) {
            capacityIncreased();
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = element;
        size++;
    }

    public void capacityIncreased() {
        capacity = array.length * 2;
        Object[] newArray = new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    public int indexOf(Object obj) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    public void remove(Object obj) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(obj)) {
                removeObjofIndex(i);
            }
        }
    }

    public void removeObjofIndex(int index) {
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
    }

    //remove Object, IndexOf, add(overloading which takes index and object and should put Object in particular index, if there is any element in index, it should rightshift
    class IteratorImpl implements Iterator {

        private int currentIndex = 0;

        public boolean hasNext() {
            if (currentIndex < size) {
                return true;
            }
            return false;
        }

        public Object next() {
            if (hasNext()) {
                return array[currentIndex++];
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        NewArrayList list = new NewArrayList();
        list.add("Hello");
        list.add("Hi");
        list.add(123);
        list.add(14.3);
        list.add(14.3);
        list.add(14.3);
        list.add(14.3);
        list.add(14.3);
        list.add(14.3);
        System.out.println("Size of the array:" + list.size);
        for (Object printElement : list) {
            System.out.println(printElement);
        } 
        list.remove("Hi");
        System.out.println("Size after removing 'Hi': " + list.size());
        System.out.println("Index of 'Hello': " + list.indexOf("Hello"));

        list.add(5, "Namaste");
        System.out.println("Size after adding at index 1: " + list.size());
        for (Object printElement : list) {
            System.out.println(printElement);
        }
        
    }

}
