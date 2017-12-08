import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int numberOpenSites;
    private int arraySize;
    private WeightedQuickUnionUF uf;
    private boolean[] openness;
    private int virtTopIndex = 0;
    private int virtBottomIndex;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Argument must be positive");
        arraySize = n;
        virtBottomIndex = n * n + 1;
        openness = new boolean[n * n + 2];
        openness[virtTopIndex] = true;
        openness[virtBottomIndex] = true;
        virtBottomIndex = n * n + 1;

        uf = new WeightedQuickUnionUF(n * n + 2);
        connectVirt();
    }


    private void connectVirt() {
        for (int i = 1; i <= arraySize; i++) uf.union(i, virtTopIndex);
        int startLastRowIndex = arraySize * arraySize - arraySize + 1;
        int endLastRowIndex = startLastRowIndex + arraySize;
        for (int i = startLastRowIndex; i < endLastRowIndex; i++) uf.union(i, virtBottomIndex);
    }

    private int toIndex(int row, int col) {
        return arraySize * (row - 1) + (col - 1) + 1;
    }

    private boolean isConnected(int p, int q) {
        return openness[p] && openness[q] && uf.connected(p, q);
    }

    private void validate(int colOrRow) {
        if (!(colOrRow > 0 && colOrRow <= arraySize)) {
            throw new IllegalArgumentException("Column or row must be between 1 and " + arraySize);
        }
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) return;

        validate(row);
        validate(col);
        int index = toIndex(row, col);
        openness[index] = true;
        numberOpenSites++;

        // union with adjacent sites
        int upperAdj = (index - arraySize) > virtTopIndex ? (index - arraySize) : -1;
        int lowerAdj = (index + arraySize) < virtBottomIndex ? (index + arraySize) : -1;
        int leftAdj = ((row - 1) * arraySize) < (index - 1) ? (index - 1) : -1;
        int rightAdj = (row * arraySize) >= (index + 1) ? (index + 1) : -1;

        int[] adjacent = new int[]{upperAdj, lowerAdj, leftAdj, rightAdj};
        for (int adj : adjacent)
            if (adj != -1 && openness[adj]) uf.union(index, adj);
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row);
        validate(col);
        int index = toIndex(row, col);
        return openness[index];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row);
        validate(col);
        int index = toIndex(row, col);
        return isConnected(index, virtTopIndex);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        if (numberOpenSites < arraySize) return false;
        return isConnected(virtTopIndex, virtBottomIndex);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation perc_1 = new Percolation(2);
        assert perc_1.numberOfOpenSites() == 0;
        assert !perc_1.percolates();

        perc_1.open(1, 1);
        assert perc_1.numberOfOpenSites() == 1;
        assert perc_1.isOpen(1, 1);
        assert !perc_1.percolates();
        assert perc_1.isFull(1, 1);

        perc_1.open(2, 2);
        assert perc_1.numberOfOpenSites() == 2;
        assert perc_1.isOpen(2, 2);
        assert perc_1.percolates();
        assert perc_1.isFull(2, 2);


        perc_1.open(1, 2);
        assert perc_1.percolates();

    }
}
