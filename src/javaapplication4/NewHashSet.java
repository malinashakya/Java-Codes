package javaapplication4;

import java.util.Iterator;

public class NewHashSet {

    public Object[] array;
    public int size;
    private int capacity = 5;

    public NewHashSet() {
        array = new Object[capacity];
        size = 0;

    }

    public NewHashSet(int capacity) {
        this.capacity = capacity;
        array = new Object[capacity];
        size = 0;
    }

    public boolean add(Object value) {
        //same old value
        if (contains(value)) {
            return false;
        }
        int index = getIndex(value);

        //If old value and new value is different
        if (array[index] != null) {
            NewHashSet collisionset = new NewHashSet(capacity * 2);
            Object oldValue = array[index];
            collisionset.add(oldValue);
            collisionset.add(value);
            array[index] = collisionset;
        } else {
            array[index] = value;
            size++;
        }
        return true;
    }

    public boolean remove(Object value) {
        //there is no such value
        if (!contains(value)) {
            return false;
        }

        int index = getIndex(value);
        if (array[index] instanceof NewHashSet) {
            NewHashSet collisionHandleSet = (NewHashSet) array[index];
            collisionHandleSet.remove(value);
            if (collisionHandleSet.size() == 0) {
                array[index] = null;
            }
        } else {
            array[index] = null;
        }

        size--;
        return true;
    }

    public int size() {
        return size;
    }

    public int getIndex(Object obj) {
        int index = Math.abs(obj.hashCode() % capacity);

        return index;
    }

    public boolean contains(Object value) {
        int index = getIndex(value);
        Object previousValue = array[index];
        //Inside CollisionHandleSet
        if (previousValue instanceof NewHashSet) {
            NewHashSet collisionHandleSet = (NewHashSet) previousValue;
            return collisionHandleSet.contains(value);
        } //If there is no CollisionHandleSet
        else {
            return (previousValue != null && previousValue.equals(value));
        }

    }

    public Iterator<Object> iterator() {
        return new Iterator<Object>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < capacity;
            }

            @Override
            public Object next() {
                return array[currentIndex++];
            }
        };
    }

    // Method to print all elements in the Outer class i.e. NewHashSet
    public void printAllElements(String tabs) {
        Iterator<Object> iterator = iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            
            if (element instanceof NewHashSet) {
                
                System.out.println(tabs + "Element: " + element);
                NewHashSet collisionHandleSet = (NewHashSet) element;
                collisionHandleSet.printAllElements(tabs + "\t");
            } else if(element!=null){
                System.out.println(tabs + "Element: " + element);
            }
        }

    }
    //Delegate method

    public void printAllElements() {
        printAllElements("");

    }

    class CollisionHandleSet {

        public Object[] array;
        public int size;

        public CollisionHandleSet() {
            array = new Object[5];
            size = 0;
        }

        public boolean add(Object value) {
            if (contains(value)) {
                return false;
            }

            int index = getIndex(value);

            array[index] = value;
            size++;
            return true;
        }

        public boolean remove(Object value) {
            if (!contains(value)) {
                return false;
            }

            int index = getIndex(value);

            array[index] = null;
            size--;
            return true;
        }

        public int size() {
            return size;
        }

        public int getIndex(Object obj) {
            int index = Math.abs(obj.hashCode() % 5);

            return index;
        }

        public boolean contains(Object value) {
            int index = getIndex(value);
            Object previousValue = array[index];
            return (previousValue != null && previousValue.equals(value));

        }

        public Iterator<Object> iterator() {
            return new Iterator<Object>() {
                private int currentIndex = 0;

                @Override
                public boolean hasNext() {
                    return currentIndex < size;
                }

                @Override
                public Object next() {
                    return array[currentIndex++];
                }
            };
        }

        // Method to print all elements in the Inner class
        public void printAllElements() {
            Iterator<Object> iterator = iterator();
            while (iterator.hasNext()) {
                Object element = iterator.next();
                System.out.println("Collision Element: " + element);
            }
        }
    }

    public static void main(String[] args) {
//        Object ob = "abc";
//        System.out.println(ob instanceof String);
        NewHashSet set = new NewHashSet();

        set.add("Hello");
        set.add(123);
        System.out.println("Added World:" + set.add("World"));
        System.out.println("Added 123:" + set.add(123));
        set.add(456);
        set.add("Malina");
        System.out.println("Added Lijala:" + set.add("Lijala"));
        set.printAllElements();
        set.remove(123);

        System.out.println("After removing 123:");
        set.printAllElements();
    }

}
