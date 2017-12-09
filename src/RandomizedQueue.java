import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int N = 0;
    private Item[] array;

    public RandomizedQueue() {

    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {

    }

    public Item dequeue() {

    }

    public Item sample() {

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
        }
    }
}
