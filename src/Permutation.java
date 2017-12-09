import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            rq.enqueue(s);
        }
        if (k < 0 || k > rq.size()) throw new IllegalArgumentException("k must be positive and lte input size");
        for (int i = 0; i < k; i++) StdOut.println(rq.dequeue());
    }
}
