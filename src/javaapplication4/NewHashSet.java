package javaapplication4;

import java.util.Iterator;

public class NewHashSet {

    public Object[][] array;
    public int size;
    public static final int CAPACITY = 25;

    public NewHashSet() {
        array = new Object[CAPACITY][];
        size = 0;
    }

    public boolean add(Object value) {
        if (contains(value)) {
            return false;
        }

        int index = getIndex(value);
        if (array[index] == null) {
            array[index] = new Object[1];
            array[index][0] = value;
        } else {
            Object[] currentArray = array[index];
            Object[] newArray = new Object[currentArray.length + 1];
            System.arraycopy(currentArray, 0, newArray, 0, currentArray.length);
            newArray[currentArray.length] = value;
            array[index] = newArray;
        }
        size++;
        return true;
    }

    public boolean remove(Object value) {
        int index = getIndex(value);
        if (array[index] != null) {
            Object[] currentArray = array[index];
            int newSize = currentArray.length - 1;
            Object[] newArray = new Object[newSize];
            int newIndex = 0;
            boolean found = false;
            for (Object obj : currentArray) {
                if (obj.equals(value)) {
                    found = true;
                    continue;
                }
                newArray[newIndex++] = obj;
            }
            if (found) {
                array[index] = newArray;
                size--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public int getIndex(Object obj) {
        int index = Math.abs(obj.hashCode() % CAPACITY);
        return index;
    }

    public boolean contains(Object value) {
        int index = getIndex(value);
        Object[] currentArray = array[index];
        if (currentArray != null) {
            for (Object obj : currentArray) {
                if (obj.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void printAllElements() {
        for (Object[] subArray : array) {
            if (subArray != null) {
                for (Object element : subArray) {
                    if (element instanceof Object[]) {
                        Iterator<Object> iterator = new NestedArrayIterator((Object[]) element);
                        while (iterator.hasNext()) {
                            System.out.println("Element: " + iterator.next());
                        }
                    } else {
                        System.out.println("Element: " + element);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Object ob = "abc";
        System.out.println(ob instanceof String);
        NewHashSet set = new NewHashSet();

        set.add("Hello");
        set.add(123);
        set.add("World");
        set.add(123); // Adding 123 again to cause collision
        set.add(456);
        set.add("Malina");
        set.add("Lijala");
        set.add("World"); // Adding World again to cause collision
        set.add(789);

        set.printAllElements();

        System.out.println("Size: " + set.size());
    }

    private static class NestedArrayIterator implements Iterator<Object> {
        private final Object[] array;
        private int index = 0;

        NestedArrayIterator(Object[] array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return index < array.length;
        }

        @Override
        public Object next() {
            return array[index++];
        }
    }
}
