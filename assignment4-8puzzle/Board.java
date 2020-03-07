/* *****************************************************************************
 *  Name:Meng Li
 *  Date:04062019 finished in 06062019
 *  Description:build a datatype Board
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Board {
    private final int nDim;
    private final int[][] board;

    public Board(int[][] blocks) {
        nDim = blocks.length;
        board = new int[nDim][nDim];
        for (int i = 0; i < nDim; i++) {
            this.board[i] = blocks[i].clone();
        }


    }

    /**
     * This method generates the solution board for the input
     *
     * @param dim the dimension of the board
     * @return solution board
     */
    private int[][] getRightBoard(int dim) {
        int[][] rBoard = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                rBoard[i][j] = dim * i + j + 1;
            }
        }
        rBoard[dim - 1][dim - 1] = 0;
        return rBoard;

    }

    /**
     * This method swap randomly two pixels in the board
     *
     * @param x1 x of first pixel
     * @param y1 y of first pixel
     * @param x2 x of second pixel
     * @param y2 y of second pixel
     * @return swaped board
     */

    private Board swap(int x1, int y1, int x2, int y2) {
        int temp;
        int[][] copyboard = new int[nDim][nDim];
        for (int i = 0; i < nDim; i++) {
            copyboard[i] = board[i].clone();
        }

        temp = copyboard[x1][y1];
        copyboard[x1][y1] = copyboard[x2][y2];
        copyboard[x2][y2] = temp;
        Board swapBoard = new Board(copyboard);

        return swapBoard;

    }

    /**
     * This method returns dimension of board
     *
     * @return dimension of board
     */

    public int dimension() {
        return nDim;
    }

    /**
     * This method calculates the sum of hamming distance between this board with solution board
     *
     * @return calculated hamming distance
     */
    public int hamming() {
        int mis = 0;
        int[][] corBoard = getRightBoard(nDim);
        for (int i = 0; i < nDim; i++) {
            for (int j = 0; j < nDim; j++) {
                if (corBoard[i][j] != 0 && corBoard[i][j] != board[i][j]) mis++;
            }
        }
        return mis;
    }

    /**
     * This method calculates the sum of manhattan distance between this board with solution board
     *
     * @return calculated manhattan distance
     */

    public int manhattan() {
        int manDistance = 0;
        int desI;
        int desJ;
        for (int i = 0; i < nDim; i++) {
            for (int j = 0; j < nDim; j++) {
                int curNumber = board[i][j];
                if (curNumber == 0 || curNumber == i * nDim + j + 1) continue;
                desI = (curNumber - 1) / nDim;
                desJ = curNumber - desI * nDim - 1;
                manDistance = manDistance + Math.abs(desI - i) + Math.abs(desJ - j);

            }
        }

        return manDistance;
    }

    /**
     * This method judges whether the board is equal to solution
     *
     * @return if equals to solution
     */

    public boolean isGoal() {
        return hamming() == 0;
    }

    /**
     * This method returns a twin of the board. A twin is a board exchanging any pair of tiles.
     *
     * @return the twin
     */

    public Board twin() {
        Board twin;
        int temp;
        int x1 = 0, y1 = 0;
        int x2 = 0, y2 = 1;
        int[][] twinArray = new int[nDim][nDim];
        // eliminate the zero
        if (board[x1][y1] == 0) {
            x1 = x1 + 1;
        }
        if (board[x2][y2] == 0) {
            x2 = x2 + 1;
        }

        for (int i = 0; i < nDim; i++) {
            twinArray[i] = board[i].clone();
        }
        temp = twinArray[x1][y1];
        twinArray[x1][y1] = twinArray[x2][y2];
        twinArray[x2][y2] = temp;
        twin = new Board(twinArray);

        return twin;
    }

    /**
     * This method judges whether two board are equal
     *
     * @param y the other board
     * @return equal or not
     */

    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null) return false;
        if (this.getClass() != y.getClass()) return false;
        Board that = (Board) y;
        if (this.board.length != that.board.length) return false;
        if (this.board[0].length != that.board[0].length) return false;

        boolean equal = Arrays.deepEquals(this.board, that.board);


        return equal;

    }

    /**
     * This method returns all neighbors of the board Neighbor means move any of the tile once
     *
     * @return iterable collection
     */

    public Iterable<Board> neighbors() {
        int blankX = 0, blankY = 0;
        int flg = 0;
        Stack<Board> boardStack = new Stack<Board>();

        // find the empty tile
        for (int i = 0; i < nDim; i++) {
            for (int j = 0; j < nDim; j++) {
                if (board[i][j] == 0) {
                    blankX = i;
                    blankY = j;
                    flg = 1;
                    break;
                }

            }
            if (flg == 1) break;
        }

        /*
         * generate(if exists) the neighbours in sequence up down left right
         */

        if (blankX > 0) {
            // Board upBoard = swap(blankX, blankY, blankX - 1, blankY);
            boardStack.push(swap(blankX, blankY, blankX - 1, blankY));

        }


        if (blankX < nDim - 1) {
            Board downBoard = swap(blankX, blankY, blankX + 1, blankY);
            boardStack.push(downBoard);
        }


        if (blankY > 0) {
            Board leftBoard = swap(blankX, blankY, blankX, blankY - 1);
            boardStack.push(leftBoard);

        }


        if (blankY < nDim - 1) {
            Board rightBoard = swap(blankX, blankY, blankX, blankY + 1);
            boardStack.push(rightBoard);

        }


        return boardStack;
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(nDim + "\n");
        for (int i = 0; i < nDim; i++) {
            for (int j = 0; j < nDim; j++) {
                s.append(String.format("%2d", board[i][j]));
                s.append(' ');
            }
            s.append('\n');
        }
        return s.toString();
    }

    public static void main(String[] args) {
        // read from files
        for (String filename : args) {

            // read in the board specified in the filename
            In in = new In(filename);
            int n = in.readInt();
            int[][] tiles = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    tiles[i][j] = in.readInt();
                }
            }


            Board b = new Board(tiles);
            StdOut.println(b);
            // StdOut.println("Board's Hamming is " + b.hamming());
            // StdOut.println("Board's Manhattan is " + b.manhattan());
            // StdOut.println("Borad is the goal " + b.isGoal());
            Board twin = b.twin();
            StdOut.println(twin);
            StdOut.println(b);
            // StdOut.println(twin);
            // StdOut.println(b);
            // Iterable<Board> sb = b.neighbors();
            // Iterator<Board> isb = sb.iterator();
            // while (isb.hasNext()) {
            //     StdOut.println(isb.next());
            // }
            // StdOut.println("the previous Board is \n" + b);
            //
            // StdOut.println(b.step);
        }
        int[][] block1 = { { 0, 4, 1 }, { 7, 5, 8 }, { 2, 6, 3 } };
        int[][] block2 = { { 0, 4, 1, 1 }, { 7, 5, 8, 1 }, { 2, 6, 3 } };
        Board ac = new Board(block1);
        Board bc = new Board(block2);
        StdOut.println(ac.equals(bc));


    }
}

