import java.util.Iterator;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int N = 0;
    private Item[] array;
    private boolean wasRemoved = false;

    public RandomizedQueue() {
        array = (Item[]) new Object[2];
    }

    private void resize(int capacity) {
        Item[] newArray = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) newArray[i] = array[i];
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
        array[N++] = item;
        wasRemoved = false;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("queue is empty");
        if (N == array.length / 4) resize(array.length / 2);

        if (!wasRemoved) StdRandom.shuffle(array, 0, N);
        Item res = array[--N];
        wasRemoved = true;
        return res;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("queue is empty");
        int randIndex = StdRandom.uniform(0, N);
        return array[randIndex];
    }

    public java.util.Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private Item[] innerArray;
        private int current = 0;

        public ArrayIterator() {
            innerArray = (Item[]) new Object[N];
            for (int i = 0; i < N; i++) {
                innerArray[i] = array[i];
            }
            StdRandom.shuffle(innerArray, 0, N);
        }

        public boolean hasNext() {
            return current < innerArray.length;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("there is no next");
            return innerArray[current++];
        }

        public void remove() {
            throw new UnsupportedOperationException("Cannot remove");
        }
    }


    public static void main(String[] args) {
        RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();
        r.enqueue(1);
        r.enqueue(2);
        r.enqueue(3);
        assert !r.isEmpty();
        for (int i : r) StdOut.println(i);
        assert r.size() == 3;
        StdOut.println(r.sample());
        assert r.size() == 3;
    }
}
