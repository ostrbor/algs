import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    private int arraySize;
    private int trials;
    private double[] fractionOfOpenSites;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials must be positive");
        }
        arraySize = n;
        this.trials = trials;
        fractionOfOpenSites = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) openRandom(perc);
            fractionOfOpenSites[i] = (double) perc.numberOfOpenSites() / (arraySize * arraySize);
        }
    }

    private void openRandom(Percolation perc) {
        int rand_row = StdRandom.uniform(arraySize) + 1;
        int rand_col = StdRandom.uniform(arraySize) + 1;
        perc.open(rand_row, rand_col);
    }

    public double mean() {
        return StdStats.mean(fractionOfOpenSites);
    }

    public double stddev() {
        return StdStats.stddev(fractionOfOpenSites);
    }

    public double confidenceLo() {
        double mean = mean();
        double stddev = stddev();
        return mean - 1.96 * stddev / Math.sqrt(trials);
    }

    public double confidenceHi() {
        double mean = mean();
        double stddev = stddev();
        return mean + 1.96 * stddev / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, T);
        StdOut.println("mean " + stats.mean());
        StdOut.println("stddev " + stats.stddev());
        StdOut.println("95% confidence interval [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");

    }
}
