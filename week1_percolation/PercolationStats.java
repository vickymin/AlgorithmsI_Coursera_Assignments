import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    private double[] count;
    private int T;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int t) {
      if (N <= 0 || t <= 0) {
          throw new IllegalArgumentException();
      }
      T = t;
      count = new double[T];
      for (int k = 0; k < T; k++) {
        Percolation percolation = new Percolation(N);
        int openSites = 0;
        while (!percolation.percolates()) {
          int x = StdRandom.uniform(N) + 1;
          int y = StdRandom.uniform(N) + 1;
          if (!percolation.isOpen(x, y)) {
            percolation.open(x, y);
            openSites++;
          }
        }
        count[k] = (double) openSites / (N*N);
      }
    }

    // sample mean of percolation threshold
    public double mean() {
      return StdStats.mean(count);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
      return StdStats.stddev(count);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
      return mean() - (1.96*stddev() / Math.sqrt(T));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
      return mean() + (1.96*stddev() / Math.sqrt(T));
    }

    // test client (described below)
    public static void main(String[] args) {
      int N = Integer.parseInt(args[0]);
      int T = Integer.parseInt(args[1]);
      PercolationStats percolationStats = new PercolationStats(N, T);
      StdOut.println("mean                     = " + percolationStats.mean());
      StdOut.println("stddev                   = " + percolationStats.stddev());
      StdOut.println("95% confidence interval  = " + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi());
    }
}
