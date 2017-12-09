import java.util.Iterator;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int head = 0;
    private int tail = 0;
    private int N = 0;
    private Item[] array;

    public RandomizedQueue() {
        array = (Item[]) new Object[1];
    }

    private void resize(int capacity) {
        Item[] newArray = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("null is illegal");
        if (N == array.length) resize(array.length * 2);
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("queue is empty");
        if (N == array.length / 4) resize(array.length / 2);

    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("queue is empty");

    }

    public static void main(String[] args) {

    }

    public ArrayIterator iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        public boolean hasNext() {
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("there is no next");
        }

        public void remove() {
            throw new UnsupportedOperationException("Cannot remove");
        }
    }
}
