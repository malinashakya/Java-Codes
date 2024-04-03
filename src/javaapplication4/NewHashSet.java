package javaapplication4;

import java.util.Iterator;

public class NewHashSet {

    public Object[] array;
    public int size;
    private int capacity = 2;

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
            NewHashSet collisionset = new NewHashSet(capacity * 3);
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
            if (currentIndex < capacity) {
                int originalIndex = currentIndex;

                if (array[currentIndex] instanceof NewHashSet) {
                    if (storedIterator == null) {
                        NewHashSet innerHashSet = ((NewHashSet) array[currentIndex]);
                        storedIterator = innerHashSet.iterator();
                    }

                    if (storedIterator.hasNext()) {
                        return true;
                    }
                }

                // Iterate over the array to find the next non-null element
                while (currentIndex < capacity) {
                    if (array[currentIndex] != null) {
                        if (array[currentIndex] instanceof NewHashSet) {
                            if (storedIterator == null) {
                                NewHashSet innerHashSet = ((NewHashSet) array[currentIndex]);
                                storedIterator = innerHashSet.iterator();
                            }

                            if (storedIterator.hasNext()) {
                                return true;
                            }
                        } else {
                            return true;
                        }
                    }
                    currentIndex++;
                }
                currentIndex = originalIndex;
//                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            /**
             * It should return the unique elements present in the array until
             * it has returned all elements
             */
            /**
             * Using one index we returned the element present inside the index
             * of the array but if there is hashSet in the current index then
             * use the iterator of the hashSet to retrieve the next element if
             * it contains remaining elements . We should maintain the
             * currentIndex to point the next element to be returned and we
             * should maintain the iterator of particular hashSet typed element
             * currently being processed.
             */

            if (hasNext()) {
                if (array[currentIndex] instanceof NewHashSet) {
                    if (storedIterator == null) {
                        NewHashSet innerHashSet = (NewHashSet) array[currentIndex];
                        storedIterator = innerHashSet.iterator();
                    }
                    if (storedIterator.hasNext()) {
                        return storedIterator.next();
                    } else {
                        storedIterator = null;
                        currentIndex++;
                        return next();
                    }
                }

                // Find the next non-null element in the array
                while (currentIndex < capacity) {
                    if (array[currentIndex] != null) {
                        Object nextElement = array[currentIndex];
                        currentIndex++;
                        return nextElement;
                    }
//                    currentIndex++;
                }
            }
            return null;
        }

    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    public void printAllElements() {
        Iterator<Object> iterator = iterator();
        System.out.println("Element: ");
        while (iterator.hasNext()) {
            Object element = iterator.next();
            System.out.println(element);
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
