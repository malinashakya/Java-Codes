package javaapplication4;

import java.util.Iterator;

public class NewHashSet {

    public Object[] array;
    public int size;
    private int capacity = 5;
    private Iterator<Object> innerIterator; // Iterator for inner sets
    private boolean iteratorCreated = false; // Flag to track iterator creation

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
        // Same old value
        if (contains(value)) {
            return false;
        }
        int index = getIndex(value);

        // If old value and new value are different
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
        // There is no such value
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
        // Inside CollisionHandleSet
        if (previousValue instanceof NewHashSet) {
            NewHashSet collisionHandleSet = (NewHashSet) previousValue;
            return collisionHandleSet.contains(value);
        } else {
            // If there is no CollisionHandleSet
            return (previousValue != null && previousValue.equals(value));
        }
    }

    class IteratorImpl implements Iterator {

        private int currentIndex = 0;
       private Iterator<Object> storedIterator = null;

        /**
         * If array[currentIndex] is of NewHashSet type, we should iterate
         * inside the NewHashSet that is made inside the array[currentIndex].
         * And after reading the elements inside the NewHashSet type which is
         * made inside the array[currentIndex] we should again move back to the
         * previous array with the index+1 and iterate in the old NewHashSet
         */
        @Override
        public boolean hasNext() {
            if (array[currentIndex] instanceof NewHashSet) {
                /**
                 * Take iterator from HashSet and store it to use the same iterator later
                 */
                  if (storedIterator == null) {
                NewHashSet innerHashSet = ((NewHashSet) array[currentIndex]);
                storedIterator = innerHashSet.iterator();
            }
               
                return storedIterator.hasNext();
            }
            while (currentIndex < capacity) {
                if (array[currentIndex] != null) {
                    return true;
                }
                currentIndex++;
            }
            return false;
        }

        @Override
        public Object next() {
            return array[currentIndex++];
        }

    }

    public Iterator<Object> iterator() {
        //defining method inside Iterator
        return new IteratorImpl();
    }

    public void printAllElements() {
        Iterator<Object> iterator = iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            if (element instanceof NewHashSet) {
                NewHashSet collisionHandleSet = (NewHashSet) element;
                collisionHandleSet.printAllElements();
            } else {
                System.out.println("Element: " + element);
            }
        }
    }

    public static void main(String[] args) {
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
