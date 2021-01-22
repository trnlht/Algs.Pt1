/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class Percolation {

    private int[] id;
    private boolean[] opened;
    private int size;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {

        if (n <= 0)
            throw new IllegalArgumentException();

        size = n;

        opened = new boolean[n * n];

        id = new int[n * n];

        for (int i = 0; i < id.length; i++)
            id[i] = i;

    }

    private int position(int row, int col) {
        if (row < 0 || row > (size - 1) || col < 0 || col > (size - 1))
            throw new IllegalArgumentException();

        return size * row + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            //Открываем саму ячейку
            int pos = position(row, col);
            opened[pos] = true;

            //Связываем с соседними открытыми ячейками
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int pos = position(row, col);

        return opened[pos];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {

    }

    // returns the number of open sites
    public int numberOfOpenSites() {

    }

    // does the system percolate?
    public boolean percolates() {

    }

    public static void main(String[] args) {

    }
}
