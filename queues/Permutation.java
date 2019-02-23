/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rquene = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            rquene.enqueue(StdIn.readString());
        }
        if (k > rquene.size())
            throw new IllegalArgumentException("Output should smaller than number of elements");
        for (int i = 0; i < k; i++) {
            StdOut.println(rquene.dequeue());
        }

    }
}
