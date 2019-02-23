/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;


public class PercolationStats {
    private Stopwatch st = new Stopwatch();
    private int cont;
    private double mean;
    private double std;
    private int trials;
    private double[] result;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException("input should be positive");
        else {
            cont = n;
            this.trials = trials;
            result = new double[trials];
            for (int i = 0; i < trials; i++) {
                Percolation p = new Percolation(n);
                while (!p.percolates()) {
                    p.open(StdRandom.uniform(1, cont + 1), StdRandom.uniform(1, cont + 1));
                }
                result[i] = p.numberOfOpenSites() / (double) (cont * cont);
                // System.out.println(p.numberOfOpensites());
            }
        }
    }

    public double mean() {
        mean = StdStats.mean(result);
        return mean;
    }

    public double stddev() {
        std = StdStats.stddev(result);
        return std;
    }

    public double confidenceLo() {
        return mean - 1.96 * Math.sqrt(std) / Math.sqrt(trials);
    }

    public double confidenceHi() {
        return mean + 1.96 * Math.sqrt(std) / Math.sqrt(trials);
    }


    public static void main(String[] args) {
        PercolationStats p1 = new PercolationStats(200, 200);
        System.out.println("mean =" + p1.mean());
        System.out.println("stddev = " + p1.stddev());
        System.out
                .println(
                        "95% confidence interval = [" + p1.confidenceLo() + ", " + p1.confidenceHi()
                                + "]");
        System.out.println("the runnung time is " + p1.st.elapsedTime());

    }
}
