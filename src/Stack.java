import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Stack {
    Node first = null;
    int size = 0;

    private class Node {
        String value;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void push(String item) {
        Node oldFirst = first;
        first = new Node();
        first.value = item;
        first.next = oldFirst;
        size++;
    }

    public String pop() {
        String item = first.value;
        first = first.next;
        size--;
        return item;
    }

    public static void main(String[] args) {
        Stack s = new Stack();
        while (!StdIn.isEmpty()) {
            String w = StdIn.readString();
            if (!w.equals("-")) s.push(w);
            else {
                String item = s.pop();
                StdOut.print(item);
            }
        }
    }
}
