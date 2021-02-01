/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

// TODO Реализовать корректную обработку n=1 и n=2

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{

    private boolean[] opened;
    private final int size;
    private final WeightedQuickUnionUF wqu;
    private int openSitesCount;
    private final int topSitePos;
    private final int bottomSitePos;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {

        if (n <= 0)
            throw new IllegalArgumentException();

        size = n;

        opened = new boolean[n * n];

        // Два дополнительных элемента для фиктивного верхнего и фиктивного нижнего участка
        wqu = new WeightedQuickUnionUF(n * n + 2);

        topSitePos = n * n;
        bottomSitePos = n * n + 1;

        //Связываем фиктивный верхний участок с верхним рядом
        for (int i = 0; i < n; i++)
            wqu.union(i, topSitePos);

        //Связываем фиктивный нижний участок с нижним рядом
        for (int i = (n - 1) * n; i < n * n; i++)
            wqu.union(i, bottomSitePos);

        openSitesCount = 0;

    }

    private int position(int row, int col)
    {
        if (row < 1 || row > size || col < 1 || col > size)
            throw new IllegalArgumentException();

        return size * (row - 1) + (col - 1);
    }

    private int[] getNeighborPositionsList(int row, int col)
    {
        int[] neighbourPositions = { -1, -1, -1, -1 };

        if (col != 1)
            neighbourPositions[0] = position(row, col - 1);

        if (col != size)
            neighbourPositions[1] = position(row, col + 1);

        if (row != 1)
            neighbourPositions[2] = position(row - 1, col);

        if (row != size)
            neighbourPositions[3] = position(row + 1, col);

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
            int[] neighborPositions = getNeighborPositionsList(row, col);

            for (int neighborPos : neighborPositions)
                if (neighborPos != -1 && opened[neighborPos])
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
    // A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites.
    public boolean isFull(int row, int col)
    {
        if (isOpen(row, col))
            return wqu.find(topSitePos) == wqu.find(position(row, col));

        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {
        return openSitesCount;
    }

    // does the system percolate?
    public boolean percolates()
    {
        return wqu.find(topSitePos) == wqu.find(bottomSitePos);
    }

    // public static void main(String[] args)
    // {
    //
    // }
}
