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
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) throw new NoSuchElementException("Dequeue is empty");
            Item item = current.value;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Cannot remove");
        }
    }

    public boolean isEmpty() {
        return first == null;
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
        size++;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Null value is illegal");
        Node oldLast = last;
        last = new Node();
        last.value = item;
        oldLast.next = last;
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Dequeue is empty");
        Node oldFirst = first;
        first = first.next;
        size--;
        return oldFirst.value;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Dequeue is empty");

    }

    public ListIterator iterator() {
        return new ListIterator();
    }

    public static void main(String[] args) {

    }
}
