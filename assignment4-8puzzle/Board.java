/* *****************************************************************************
 *  Name:Meng Li
 *  Date:04062019 finished in 06062019
 *  Description:build a datatype Board
 **************************************************************************** */

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class Board implements Comparable<Board> {
    private int nDim;
    private int[][] board;
    private int[][] rightBoard;
    private int step = 0;

    public Board(int[][] blocks) {
        this.board = blocks.clone();
        nDim = this.board.length;
        rightBoard = new int[nDim][nDim];
        getRightBoard(nDim);

    }

    private void getRightBoard(int dim) {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                rightBoard[i][j] = dim * i + j + 1;
            }
        }
        rightBoard[dim - 1][dim - 1] = 0;

    }

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

    public int dimension() {
        return nDim;
    }

    public int hamming() {
        int mis = 0;
        for (int i = 0; i < nDim; i++) {
            for (int j = 0; j < nDim; j++) {
                if (rightBoard[i][j] != 0 && rightBoard[i][j] != board[i][j]) mis++;
            }
        }
        return mis;
    }

    public int manhattan() {
        int man_dis = 0;
        int des_i;
        int des_j;
        for (int i = 0; i < nDim; i++) {
            for (int j = 0; j < nDim; j++) {
                int cur_num = board[i][j];
                if (cur_num == 0 || cur_num == i * nDim + j + 1) continue;
                des_i = (cur_num - 1) / nDim;
                des_j = cur_num - des_i * nDim - 1;
                man_dis = man_dis + Math.abs(des_i - i) + Math.abs(des_j - j);

            }
        }

        return man_dis;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public Board twin() {
        Board twin;
        int temp;
        int x1 = 0, y1 = 0;
        int x2 = 0, y2 = 0;
        int[][] twinArray = new int[nDim][nDim];

        while ((x1 == x2 && y1 == y2) || board[x1][y1] == 0 || board[x2][y2] == 0) {
            x1 = StdRandom.uniform(nDim);
            x2 = StdRandom.uniform(nDim);
            y1 = StdRandom.uniform(nDim);
            y2 = StdRandom.uniform(nDim);
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

    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null) return false;
        if (this.getClass() != y.getClass()) return false;
        Board that = (Board) y;

        return this.board
                == that.board;                                        //board is a private variable
    }

    public Iterable<Board> neighbors() {
        int blankX = 0, blankY = 0;
        int flg = 0;
        Stack<Board> boardStack = new Stack<Board>();


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

        /**
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

    /**
     * @param that another Board to be compared
     */

    public int compareTo(Board that) {
        int thisPri;
        int thatPri;
        thisPri = this.manhattan() + step;
        thatPri = that.manhattan() + that.step;
        if (thisPri > thatPri) return 1;
        if (thatPri > thisPri) return -1;
        return 0;

    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(nDim + "\n");
        for (int i = 0; i < nDim; i++) {
            for (int j = 0; j < nDim; j++) {
                s.append(String.format("%2d", board[i][j]));
            }
            s.append('\n');
        }
        return s.toString();
    }

    public static void main(String[] args) {
        int[][] block = { { 8, 0, 3 }, { 4, 1, 2 }, { 7, 6, 5 } };
        Board b = new Board(block);
        StdOut.println(b);
        // StdOut.println("Board's Hamming is " + b.hamming());
        // StdOut.println("Board's Manhattan is " + b.manhattan());
        // StdOut.println("Borad is the goal " + b.isGoal());
        // Board twin = b.twin();
        // StdOut.println(twin);
        // StdOut.println(b);
        Iterable<Board> sb = b.neighbors();
        Iterator<Board> isb = sb.iterator();
        while (isb.hasNext()) {
            StdOut.println(isb.next());
        }
        StdOut.println("the previous Board is \n" + b);

        StdOut.println(b.step);

    }
}

