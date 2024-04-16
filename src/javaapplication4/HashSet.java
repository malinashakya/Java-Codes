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

public class HashSet<T> implements Iterable<T> {

    int size = 0;
    int capacity = 5;
    private Object[] array = new Object[capacity];

    public boolean add(T obj) {
        if (contains(obj)) {
            return false;
        }
        int index = getIndex(obj);
        array[index] = obj;
        size++;
        return true;
    }

    public boolean contains(T obj) {
        int index = getIndex(obj);
        return array[index] != null && array[index].equals(obj);
    }

    public int getIndex(T obj) {
        int hashcode = Math.abs(obj.hashCode() % array.length);
        return hashcode;
    }

    public int size() {
        return size;
    }
    
    private class IteratorClass implements Iterator<T> {
        int currentIndex = 0;
    
        public boolean hasNext() {
            return currentIndex < size;
        }
        
        public T next() {
            if (hasNext()) {
                return (T) array[currentIndex++];
            }
            return null;
        }
    }
    public  Iterator<T> iterator(){
        return new IteratorClass();
    }
    

    public static void main(String[] args) {
        // TODO code application logic here
        HashSet<String> set = new HashSet<String>();
        Iterator<String> iterate = set.iterator();
        set.add("A");
        set.add("B");
        set.add("C");
//        set.add("C");
//        String A = "abcd";//memory ko value ==
//        String B = "abcd";//reference value.equals()
//        int i = 100;
//        int j = 100;//Primitive data type
//        new Integer(100);//object data typr
        System.out.println("Size of this set is: " + set.size());
        System.out.println("Elements inside the hashset: ");
        
        while (iterate.hasNext()){
//            System.out.println(iterate.hasNext());
          Object element = iterate.next();
            System.out.println(element);
        }

    }

}