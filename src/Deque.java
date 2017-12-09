import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private Node beforeLast;
    private int size;

    public Deque() {
        first = null;
        last = null;
        beforeLast = null;
        size = 0;
    }

    private class Node {
        Item value;
        Node next;
        Node previous;
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("There is no next item");
            Item item = current.value;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Cannot remove");
        }
    }

    public boolean isEmpty() {
        return (first == null || last == null);
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Null value is illegal");

        Node oldFirst = first;
        first = new Node();
        first.value = item;
        first.next = oldFirst;
        first.previous = null;

        if (isEmpty()) last = first;
        else oldFirst.previous = first;
        size++;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Null value is illegal");

        Node oldLast = last;
        last = new Node();
        last.value = item;
        last.next = null;
        last.previous = oldLast;

        if (isEmpty()) first = last;
        else oldLast.next = last;
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Dequeue is empty");

        Node oldFirst = first;
        first = first.next;
        if (isEmpty()) last = null;
        else first.previous = null;

        size--;
        return oldFirst.value;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Dequeue is empty");

        Node oldLast = last;
        last = oldLast.previous;
        if (isEmpty()) first = null;
        else last.next = null;

        size--;
        return oldLast.value;

    }

    public java.util.Iterator<Item> iterator() {
        return new ListIterator();
    }

    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();
        d.addFirst(1);
        assert d.size() == 1;
        assert !d.isEmpty();

        d.addLast(2);
        assert d.size() == 2;
        assert !d.isEmpty();

        int i = d.removeFirst();
        assert i == 1;

        int j = d.removeLast();
        assert j == 2;

        assert d.isEmpty();

        d.addLast(2);
        d.addFirst(1);
        for (int itm : d) StdOut.println(itm);
    }
}
