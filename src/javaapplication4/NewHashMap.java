/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication4;

/**
 *
 * @author malin
 */
public class NewHashMap {

    int capacity = 5;
    public Object[] table;
    public int size;

    public NewHashMap() {
        table = new Object[capacity];
        size = 0;
    }

    public NewHashMap(int capacity) {
        this.capacity = capacity;
        table = new Object[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int hash(Object obj) {
        int index = Math.abs(obj.hashCode() % capacity);
        return index;
    }

    public static class KeyValue {

        Object key;
        Object value;
        KeyValue next;

        public KeyValue(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(Object key, Object value) {
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new KeyValue(key, value);
            size++;
        } else {
            KeyValue current = (KeyValue) table[index];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            current.next=new KeyValue(key, value);
            size++;
        }
    }

    public void printAllElements() {
        for (Object entry : table) {
            if (entry != null) {
                if (entry instanceof KeyValue) {
                    KeyValue current = (KeyValue) entry;
                    while (current != null) {
                        System.out.println("Key: " + current.key + ", Value: " + current.value);
                        current = current.next;
                    }
                } else if (entry instanceof NewHashMap) {
                    NewHashMap collisionset = (NewHashMap) entry;
                    collisionset.printAllElements();
                }
            }
        }
    }

    public static void main(String[] args) {
        NewHashMap map = new NewHashMap();
        map.put("Hello", 1);
        map.put("World", 2);
        map.put(123, "Namaste");
        map.put(456, "Hi");

        System.out.println("Size of the map: " + map.size());
        System.out.println("Check purpose");

        map.printAllElements();
    }
}
