/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class PercolationStats
{
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {

    }

    // sample mean of percolation threshold
    public double mean()
    {

    }


    // sample standard deviation of percolation threshold
    public double stddev()
    {

    }

    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {

    }

    // test client (see below)
    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[1]);  //Размерность сетки
        int T = Integer.parseInt(args[2]);  //Количество испытаний

        PercolationStats ps = new PercolationStats(n, T);
    }
}

