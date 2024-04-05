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
    int capacity=5;
    public Object[] table;
    public int size;
    public NewHashMap()
    {
        table=new Object[capacity];
        size=0;
    }
     public NewHashMap(int capacity) {
        this.capacity = capacity;
        table = new Object[capacity];
        size = 0;
    }
    public int size() {
        return size;
    }

    public int getIndex(Object obj) {
        int index = Math.abs(obj.hashCode() % capacity);
        return index;
    }
    public static class KeyValue{
        Object key;
        Object value;
        KeyValue next;
        public KeyValue(Object key, Object value)
        {
            this.key=key;
            this.value=value;
        }
    }
    public void put(Object key, Object value)
    {
        int index=getIndex(key);
        if(table[index]==null)
        {
            table[index]=new KeyValue(key,value);
            size++;
        }
        else{
            NewHashMap collisionset=new NewHashMap(capacity *2);
            Object oldvalue=table[index];
            collisionset.put(oldvalue.key, oldvalue.value);
            //key->hashValue value->KeyValue
        }
    }
}
