public class Stack {
    Node first;
    int size;

    public Stack() {
        first = null;
        size = 0;
    }

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

    public String pop() throws Exception {
        if (isEmpty()) throw new Exception("Already empty");
        Node oldFirst = first;
        first = first.next;
        size--;
        return oldFirst.value;
    }

    public static void main(String[] args) {
        Stack s = new Stack();
        assert s.isEmpty();
        s.push("a");
        assert s.size() == 1;
        assert !s.isEmpty();
        String res = null;
        try {
            res = s.pop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert res == "a";
        try {
            s.pop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
