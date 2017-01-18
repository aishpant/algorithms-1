import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private int size;
    private int openSites;
    private WeightedQuickUnionUF quickUnionUF;
    private int firstRow;
    private int lastRow;

    public Percolation(final int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Size of grid is greater than 0");
        size = n + 1;
        grid = new boolean[size][size];
        openSites = 0;
        firstRow = n * n;
        lastRow = (n * n) + 1;
        quickUnionUF = new WeightedQuickUnionUF((n * n) + 2);
    }

    public void open(final int row, final int col) {
        throwException(row, col);

        if (!grid[row][col]) {
            grid[row][col] = true;
            openSites++;
            if (row == 1) {
                quickUnionUF.union(firstRow, xyTo1D(row, col));
            }
            if (row == size - 1) {
                quickUnionUF.union(lastRow, xyTo1D(row, col));
            }
            connectNeighbours(row, col);
        }
    }

    public boolean isOpen(final int row, final int col) {
        throwException(row, col);
        return grid[row][col];
    }

    public boolean isFull(final int row, final int col) {
        throwException(row, col);
        return (openSites > 0) && quickUnionUF.connected(firstRow, xyTo1D(row, col));
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return quickUnionUF.connected(firstRow, lastRow);
    }

    private int xyTo1D(final int row, final int col) {
        return (row - 1) * (size - 1) + (col - 1);
    }

    private void connectNeighbours(final int row, final int col) {
        int idx = xyTo1D(row, col);
        if (top(row, col) != -1 && isOpen(top(row, col), col)) {
            quickUnionUF.union(idx, xyTo1D(top(row, col), col));
        }
        if (left(row, col) != -1 && isOpen(row, left(row, col))) {
            quickUnionUF.union(idx, xyTo1D(row, left(row, col)));
        }
        if (right(row, col) != -1 && isOpen(row, right(row, col))) {
            quickUnionUF.union(idx, xyTo1D(row, right(row, col)));
        }
        if (bottom(row, col) != -1 && isOpen(bottom(row, col), col)) {
            quickUnionUF.union(idx, xyTo1D(bottom(row, col), col));
        }
    }

    private int top(final int row, final int col) {
       return (row - 1) >= 1 ? (row - 1) : -1;
    }

    private int left(final int row, final int col) {
        return (col - 1) >= 1 ? (col - 1) : -1;
    }

    private int right(final int row, final int col) {
        return (col + 1) < size ? (col + 1) : -1;
    }

    private int bottom(final int row, final int col) {
        return (row + 1) < size ? (row + 1) : -1;
    }

    private void throwException(final int row, final int col) {
        if (row <= 0 || row >= size || col <= 0 || col >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }
}
