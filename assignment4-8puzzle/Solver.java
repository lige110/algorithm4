/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    private int steps = 0;
    private MinPQ<Board> pq;
    private Stack<Board> solutionBoard = new Stack<Board>();


    public Solver(Board initial) {
        if (initial == null)
            throw new java.lang.IllegalArgumentException("input Board can't be null!");
        

    }

    public boolean isSolvable() {
        return true;
    }

    public int moves() {
        return steps;
    }

    public Iterable<Board> solution() {
        return solutionBoard;

    }

    public static void main(String[] args) {

    }
}
