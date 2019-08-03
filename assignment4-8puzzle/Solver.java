/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Solver {
    private MinPQ<Node> pq1 = new MinPQ<Node>();
    private MinPQ<Node> pq2 = new MinPQ<Node>();
    private Stack<Board> solutionBoard = new Stack<Board>();
    private Node finalNode;

    private class Node implements Comparable<Node> {
        private final int step;
        private final int priority;
        private final Board board;
        private final Node parent;

        public Node(Board bd, Node parent) {
            this.board = bd;
            this.parent = parent;
            if (parent == null) {
                this.step = 0;
            }
            else {
                this.step = this.parent.step + 1;

            }
            this.priority = board.manhattan() + step;
        }

        @Override
        public int compareTo(Node that) {
            if (this.priority > that.priority) return 1;
            else if (this.priority == that.priority) return 0;
            else return -1;
        }

    }

    /***
     *
     * @param initial initial board for the game
     * @var flag to indicate if we have found the correct board
     */
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("input Board can't be null!");
        }

        Node initialNode1 = new Node(initial, null);
        Node initialNode2 = new Node(initial.twin(), null);
        Node searchNode;
        Node insertNode;
        // initialize the PQ
        pq1.insert(initialNode1);
        pq2.insert(initialNode2);

        while (true) {
            // search the first board
            searchNode = pq1.delMin();
            if (searchNode.board.isGoal()) {
                finalNode = searchNode;
                break;
            }
            else {
                Iterable<Board> neighbourBoards = searchNode.board.neighbors();
                Iterator<Board> neighbourBoardsIterator = neighbourBoards.iterator();
                while (neighbourBoardsIterator.hasNext()) {
                    insertNode = new Node(neighbourBoardsIterator.next(), searchNode);
                    if (insertNode.equals(searchNode.parent)) continue;
                    pq1.insert(insertNode);
                }
            }

            // jump to search the other Board
            searchNode = pq2.delMin();
            if (searchNode.board.hamming() == 0) {
                finalNode = null;
                break;
            }
            else {
                Iterable<Board> neighbourBoards = searchNode.board.neighbors();
                Iterator<Board> neighbourBoardsIterator = neighbourBoards.iterator();
                while (neighbourBoardsIterator.hasNext()) {
                    insertNode = new Node(neighbourBoardsIterator.next(), searchNode);
                    if (insertNode == searchNode.parent) continue;
                    pq2.insert(insertNode);
                }
            }


        }


    }

    public boolean isSolvable() {
        return finalNode != null;
    }

    public int moves() {
        if (finalNode == null) return -1;
        return finalNode.step;
    }

    public Iterable<Board> solution() {

        Node temp = finalNode;

        while (temp.parent != null) {
            solutionBoard.push(temp.board);
            temp = temp.parent;

        }

        return solutionBoard;

    }

    public static void main(String[] args) {
        int[][] block = { { 0, 1, 3 }, { 4, 2, 5 }, { 6, 8, 7 } };
        Board b = new Board(block);
        Solver s = new Solver(b);
        // Iterable<Board> solution = s.solution();
        // Iterator<Board> solutionIterator = solution.iterator();
        // while (solutionIterator.hasNext()) {
        //     StdOut.println(solutionIterator.next());
        // }
        StdOut.println(s.solution());
    }
}
