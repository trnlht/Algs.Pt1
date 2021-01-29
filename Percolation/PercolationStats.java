/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

// TODO Добавить вывод в консоль

public class PercolationStats
{
    private double[] thresholds;
    double m;
    double s;
    double confLo;
    double confHi;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();

        thresholds = new double[trials];

        for (int i = 0; i < trials; i++)
        {
            Percolation p = new Percolation(n);

            //Выбираем случайную ячейку
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);

            while (!p.percolates())
            {
                //Открываем только ещё не открытую ячейку
                while (p.isOpen(row, col))
                {
                    row = StdRandom.uniform(1, n + 1);
                    col = StdRandom.uniform(1, n + 1);
                }

                p.open(row, col);
            }

            thresholds[i] = (double) p.numberOfOpenSites() / (n * n);
        }

        m = StdStats.mean(thresholds);
        s = StdStats.stddev(thresholds);

        confLo = (m - (1.96 * s) / Math.sqrt(trials));
        confHi = (m + (1.96 * s) / Math.sqrt(trials));
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return m;
    }


    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return s;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return confLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return confHi;
    }

    // test client (see below)
    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);  //Размерность сетки
        int T = Integer.parseInt(args[1]);  //Количество испытаний

        PercolationStats ps = new PercolationStats(n, T);

        System.out.println("mean = " + Double.toString(ps.mean()));
        System.out.println("stddev = " + Double.toString(ps.stddev()));
        System.out.println("95% confidence interval = [" +
                                   Double.toString(ps.confidenceLo()) + ", " +
                                   Double.toString(ps.confidenceHi()) + "]"
        );


    }
}

