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
public class NewArrayList implements Iterable<Object>{
    int capacity=5;
    Object[] array;
    int size;
    public NewArrayList()
    {
        array=new Object[capacity];
        size=0;
    }
    public int size()
    {
        return size;
    }
    public void add(Object element)
    {
        if(size==array.length)
        {
            capacityIncreased();
        }
        array[size++]=element;
    }
    public void capacityIncreased()
    {
        int newCapacity=array.length*2;
        Object[] newArray=new Object[newCapacity];
        System.arraycopy(array,0,newArray,0,size);
        array=newArray;
    }
    
     public Iterator<Object> iterator() {
        return new IteratorImpl();
    }
     class IteratorImpl implements Iterator{
                 private int currentIndex = 0;
                  public boolean hasNext() {
                      if(currentIndex<size)
                      {
                          return true;
                      }
                      return false;
                  }
                  public Object next() {
                      if(hasNext())
                      {
                          return array[currentIndex++];
                      }
                      return 0;
                  }
     }
    public static void main(String[] args) {
        NewArrayList list=new NewArrayList();
        list.add("Hello");
        list.add("Hi");
        list.add(123);
        list.add(14.3);
        System.out.println("Size of the array:"+list.size);
        for(Object printElement:list)
        {
            System.out.println(printElement);
        }
    }
            
}
