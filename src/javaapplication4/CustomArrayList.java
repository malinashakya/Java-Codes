/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication4;

/**
 *
 * @author malin
 */
import java.util.Iterator;
public class CustomArrayList implements Iterable<Object> {

    int size;
    int capacity = 5;
    Object[] array;

    CustomArrayList() {
        size = 0;
        array = new Object[capacity];
    }

    public void addElement(Object element) {
        if (size == array.length) {
            increaseCapacity();
        }
        array[size++] = element;
    }

    public void increaseCapacity() {
        capacity = capacity * 2;
        Object[] newArray = new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    public int size() {
        return size;
    }

    public void remove(Object element) {
        for (int index = 0; index < size; index++) {
            if (array[index].equals(element)) {
                for (int j = index; j < size - 1; j++) {
                    array[j] = array[j + 1];
                }
                array[size-1]=null;
                size--;
            }
        }

    }
    public void addElementAtIndex(int index,Object element)
    {
        if(index>size||index<0)
        {
            throw new Error("Index is not found");
        }
        for(int i=size;i>index;i--)
        {
            array[i]=array[i-1];
        }
        if(size==array.length)
        {
            increaseCapacity();
        }
        array[index]=element;
        size++;
    }
    class IteratorImplementation implements Iterator{
        int currentIndex=0;
        public boolean hasNext()
        {
            if(currentIndex<size)
            {
                return true;
            }
            return false;
        }
        public Object next()
        {
            if(hasNext())
            {
                return array[currentIndex++];
            }
            return 0;
        }
    }
    public Iterator<Object> iterator()
    {
        return new IteratorImplementation();
    }
    public static void main(String[] args) {
        CustomArrayList arraylist = new CustomArrayList();
        arraylist.addElement("Hello");
        arraylist.addElement(123);
        arraylist.addElement("Hello");
        arraylist.addElement(133);
        System.out.println("Size:"+arraylist.size());
        arraylist.addElement("Hello");
        arraylist.addElement(45);
        System.out.println("Size after adding word Hello and 123: "+arraylist.size());
        arraylist.remove("Hello");
        System.out.println("Size: after removing word Hello: "+arraylist.size());
        arraylist.remove("Hi");
        System.out.println("Size: after removing word Hi: "+arraylist.size());
        arraylist.addElementAtIndex(1,"Namaste");
        System.out.println("Size after adding word Namaste :"+arraylist.size());
        for(Object printElements:arraylist)
        {
            System.out.println(printElements);
        }
    }
}
