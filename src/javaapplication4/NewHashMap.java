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
            NewHashMap collisionset = new NewHashMap(capacity * 2);
            Object oldvalue = table[index];
            collisionset.put(hash(oldvalue), (KeyValue) oldvalue);
            //key->hashValue value->KeyValue
            //if same key overwrite
            KeyValue newValue = new KeyValue(key, value);
            collisionset.put(hash(key), newValue);
            table[index] = collisionset;
        }
    }

    public Object get(Object key) {
        int index = hash(key);
        KeyValue current = (KeyValue) table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return "Key not found";
    }

    public void remove(Object key) {
        int index = (hash(key));
        if (table[index] != null) {
            KeyValue prev = null;
            KeyValue current = (KeyValue) table[index];
            while (current != null) {
                if (current.key.equals(key)) {
                    if (prev == null) {
                        table[index] = current.next;
                    } else {
                        prev.next = current.next;
                    }
                    size--;
                }
                prev = current;
                current = current.next;
            }
        }
    }

    public void printAllElements() {
        for (Object entry : table) {
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

    public static void main(String[] args) {
        NewHashMap map = new NewHashMap();
        map.put("Hello", 1);
        map.put("World", 2);
        map.put(123, "Namaste");
        map.put(456, "Hi");

        System.out.println("Size of the map: " + map.size());
        System.out.println("Check purpose");
        System.out.println("Value for key 'Hello': " + map.get("Hello"));
        System.out.println("Value for key 'Namaste': " + map.get("Namaste"));

        map.remove("World");
        System.out.println("Size of the map after removing 'World': " + map.size());
        map.printAllElements();
    }
}
