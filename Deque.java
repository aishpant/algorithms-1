import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The one where AP learns how to write java docs
 * Created by @author aishwarya.pant on 19/01/17.
 */
public class Deque<Item> implements Iterable<Item> {

    private int n;          // number of elements in deque
    private Node first;     // first item of deque
    private Node last;      // last item of deque

    // helper list class
    private class Node {
        private Item item;  //
        private Node next;  // pointer to the next node
        private Node prev;  // pointer to the previous node
    }

    /**
     * Initialize an empty deque
     */
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    /**
     * @return true if deque is empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null || last == null;
    }

    /**
     * @return the number of items in the deque
     */
    public int size() {
        return n;
    }

    /**
     * Add item to the beginning of the deque
     * @param item the item to add
     */
    public void addFirst(final Item item) {
        checkIfItemIsNull(item);
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        if (isEmpty()) {
            last = first;
        } else {
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        n++;
    }

    /**
     * Add item to the end of the deque
     * @param item the item to be added
     */
    public void addLast(Item item) {
        checkIfItemIsNull(item);
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            last.prev = oldLast;
            oldLast.next = last;
        }
        n++;
    }

    /**
     * Remove item from the front of the deque
     * @return the item that was removed
     */
    public Item removeFirst() {
        checkIfDequeEmpty();
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) {
            last = first;
        } else {
            first.prev = null;
        }
        return item;
    }

    /**
     * Remove item from the end of the deque
     * @return the item that was removed
     */
    public Item removeLast() {
        checkIfDequeEmpty();
        Item item = last.item;
        last = last.prev;
        n--;
        if (isEmpty()) {
            first = last;
        } else {
            last.next = null;
        }
        return item;
    }

    /**
     * Returns an iterator that iterates over the items in order from first to last
     * @return an iterator that iterates over the items in order from first to last
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;   // current element in the iterator

        /**
         * Does the list have another item?
         * @return true if another item is present; false otherwise
         */
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Throw an UnsupportedOperationException if iterator tries to remove an item
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * Get the next item in the list
         * @return the next item in the list
         */
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    /**
     * Throws a NullPointerException if a null item is being added
     * @param item the item to be added
     */
    private void checkIfItemIsNull(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    /**
     * Throws a NoSuchElementException if remove is called on an empty deque
     */
    private void checkIfDequeEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Unit tests the {@code Deque} data type
     * @param args the command line arguments
     */
    public static void main(final String[] args) {

    }
}