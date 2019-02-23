/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private int cont;
    private int[][] grid;
    private WeightedQuickUnionUF uf;
    private int nopen = 0;


    public Percolation(int n) {
        if (n <= 0) throw new java.lang.IllegalArgumentException("n should be positive");
        else {
            cont = n;
            uf = new WeightedQuickUnionUF((int) Math.pow(cont, 2) + 2);
            mvirtualtopend(uf);
            grid = new int[cont][cont];


        }
    }


    // validate if the input indices are valid
    private void validate(int p) {
        if (p <= 0 || p > cont)
            throw new IllegalArgumentException("Indices should be within range");
    }

    public void open(int row, int col) {
        validate(row);
        validate(col);
        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = 1;
            nopen++;
            int point;
            int temp = xyTo1D(row, col);
            if ((row - 1) > 0 && grid[row - 2][col - 1] == 1) {
                point = xyTo1D(row - 1, col);
                uf.union(point, temp);
            }
            if ((col - 1) > 0 && grid[row - 1][col - 2] == 1) {
                point = xyTo1D(row, col - 1);
                uf.union(point, temp);
            }
            if ((row + 1) <= cont && grid[row][col - 1] == 1) {
                point = xyTo1D(row + 1, col);
                uf.union(point, temp);
            }
            if ((col + 1) <= cont && grid[row - 1][col] == 1) {
                point = xyTo1D(row, col + 1);
                uf.union(point, temp);
            }
        }


    }


    public boolean isOpen(int row, int col) {
        validate(row);
        validate(col);
        return grid[row - 1][col - 1] == 1;
    }

    public boolean isFull(int row, int col) {
        return uf.connected(xyTo1D(row, col), cont * cont) && grid[row - 1][col - 1] == 1;
    }

    public int numberOfOpenSites() {
        return nopen;
    }

    public boolean percolates() {
        if (cont == 1) {
            return false;
        }
        return uf.connected(cont * cont, cont * cont + 1);
    }

    // change 2D ordinate to 1D ordinate
    private int xyTo1D(int row, int col) {
        validate(row);
        validate(col);
        return (row - 1) * cont + col - 1;

    }


    private void mvirtualtopend(WeightedQuickUnionUF ofo) {
        for (int i = 0; i < cont; i++) {
            ofo.union(i, cont
                    * cont);                                                    // connect top to virtual top
            ofo.union(cont * (cont - 1) + i,
                      cont * cont
                              + 1);                                         // connect end to virtual end
        }

    }


    public static void main(String[] args) {
        Percolation perc = new Percolation(4);
        perc.open(1, 1);
        perc.open(2, 3);
        perc.open(1, 2);
        perc.open(1, 3);
        if (perc.uf.connected(perc.xyTo1D(2, 2), perc.xyTo1D(2, 3))) {
            System.out.println("it works");
        }
        if (perc.isFull(2, 3)) {
            System.out.println("it is full");
        }

    }
}
