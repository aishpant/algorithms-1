import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The one where AP learns how to write java docs
 * Created by @author aishwarya.pant on 19/01/17.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INITIAL_SIZE = 2;
    private int n;
    private Item[] items;

    /**
     * Initializes an empty RandomizedDeque
     */
    public RandomizedQueue() {
        items = (Item[]) new Object[INITIAL_SIZE];
        n = 0;
    }

    /**
     * Is the queue empty?
     * @return true if the queue is empty; false otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of items on the queue
     * @return the number of the items in the list
     */
    public int size() {
        return n;
    }

    /**
     * Adds an item
     * @param item the item to be added
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (n > 0 && n == items.length) {
            resize(2 * items.length);
        }
        items[n++] = item;
    }

    /**
     * Resizes the array for memory efficiency
     * @param size the size of the new array
     */
    private void resize(int size) {
        Item[] copy = (Item[]) new Object[size];
        for (int i = 0; i < n; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    /**
     * Removes and returns a random item
     * @return the item that was removed
     */
    public Item dequeue() {
        checkIfDequeEmpty();
        int idx = StdRandom.uniform(n);
        Item item = items[idx];
        items[idx] = items[--n];
        if (n > 0 && n == items.length/4) {
            resize(items.length/2);
        }
        return item;
    }

    /**
     * Returns but does not remove a random item
     * @return a random item from the queue
     */
    public Item sample() {
        checkIfDequeEmpty();
        int idx = StdRandom.uniform(n);
        return items[idx];
    }

    /**
     * Returns an independent iterator over items in random order
     * @return an independent iterator over items in random order
     */
    public Iterator<Item> iterator() {
        return new ListIterator(items);
    }

    private class ListIterator implements Iterator<Item> {
        private Item[] copy;    // copy of the items list
        private int i = 0;          // to track the iterator

        public ListIterator(Item[] items) {
            copy = (Item[]) new Object[n];
            for (int k = 0; k < n; k++) {
                copy[k] = items[k];
            }
        }

        /**
         * Returns a random item from the list
         * @return a random item from the list
         */
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int idx = StdRandom.uniform(copy.length - i);
            Item item = copy[idx];
            copy[idx] = copy[n-i-1];
            copy[n-i-1] = null;
            i++;
            return item;
        }

        /**
         * Returns true if the list has an item; false otherwise
         * @return true if the list has an item; false otherwise
         */
        public boolean hasNext() {
            return i < n;
        }

        /**
         * Throws UnsupportedOperationException if iterator tries to remove an item
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Throws a NoSuchElementException if remove is called on an empty queue
     */
    private void checkIfDequeEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Unit tests the {@code RadomizedDeque} data type
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }
}
