/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

//TODO Добавить фиктивные участки сверху и снизу сетки


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;

public class Percolation
{

    private int[] id;
    private boolean[] opened;
    private int size;
    private WeightedQuickUnionUF wqu;
    private int openSitesCount;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {

        if (n <= 0)
            throw new IllegalArgumentException();

        size = n;

        opened = new boolean[n * n];

        wqu = new WeightedQuickUnionUF(n * n);

        openSitesCount = 0;

        // id = new int[n * n];
        //
        // for (int i = 0; i < id.length; i++)
        //     id[i] = i;

    }

    private int position(int row, int col)
    {
        if (row < 1 || row > size || col < 1 || col > size)
            throw new IllegalArgumentException();

        return size * (row - 1) + (col - 1);
    }

    private ArrayList<Integer> getNeighborPositionsList(int row, int col)
    {
        ArrayList<Integer> neighbourPositions = new ArrayList<>();

        if (col != 1)
            neighbourPositions.add(position(row, col - 1));

        if (col != size)
            neighbourPositions.add(position(row, col + 1));

        if (row != 1)
            neighbourPositions.add(position(row - 1, col));

        if (row != size)
            neighbourPositions.add(position(row + 1, col));

        return neighbourPositions;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        if (!isOpen(row, col))
        {
            //Открываем саму ячейку
            int selfPos = position(row, col);
            opened[selfPos] = true;

            //Связываем с соседними открытыми(!) ячейками
            ArrayList<Integer> neighborPositions = getNeighborPositionsList(row, col);

            for (int neighborPos : neighborPositions)
                if (opened[neighborPos])
                    wqu.union(selfPos, neighborPos);

            openSitesCount++;
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        int pos = position(row, col);

        return opened[pos];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {

    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {
        return openSitesCount;
    }

    // does the system percolate?
    public boolean percolates()
    {

    }

    public static void main(String[] args)
    {

    }
}
