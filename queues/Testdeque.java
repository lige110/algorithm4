/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Testdeque {
    public void testinterator() {
        Deque<Integer> dq = new Deque<Integer>();
        for (int i = 0; i < 199; i++) {
            dq.addFirst(i);
        }
        Iterator<Integer> my = dq.iterator();
        while (my.hasNext()) {
            StdOut.println(my.next());
        }

    }


    public static void main(String[] args) {
        Testdeque test = new Testdeque();
        test.testinterator();

    }
}
