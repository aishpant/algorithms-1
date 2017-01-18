import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private double[] openSites;
    private int trials;
    private static final double Z_VALUE = 1.96;

    public PercolationStats(final int num, final int trials) {
        throwException(num, trials);
        Percolation p;
        this.openSites = new double[trials];
        this.trials = trials;
        for (int i = 0; i < trials; i++) {
            p = new Percolation(num);
            while (!p.percolates()) {
                int x = StdRandom.uniform(1, num + 1);
                int y = StdRandom.uniform(1, num + 1);
                p.open(x, y);
            }
            openSites[i] = 1.0 * p.numberOfOpenSites() / (num * num);
        }

    }

    public double mean() {
        return StdStats.mean(openSites);
    }

    public double stddev() {
        if (trials == 1) {
            return Double.NaN;
        } else {
            return StdStats.stddev(openSites);
        }
    }

    public double confidenceLo() {
        return mean() - (Z_VALUE * stddev() / Math.sqrt(trials));
    }

    public double confidenceHi() {
        return mean() + (Z_VALUE * stddev() / Math.sqrt(trials));
    }

    public static void main(final String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, trials);
        StdOut.println("mean = " + percolationStats.mean());
        StdOut.println("stddev = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = " + percolationStats.confidenceLo()
                + ", " + percolationStats.confidenceHi());
    }

    private void throwException(final int n, final int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Arguments passed are incorrect");
        }
    }
}
