/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class test {
    public int val = 10;

    public int ha(int a) {
        a = a - 1;
        return a;
    }

    public static void min(test test) {
        test.val = test.val - 1;
    }


    public static void main(String[] args) {
        int a = 19;
        test t = new test();
        StdOut.println(t.val);
        test.min(t);
        StdOut.println(t.val);


    }
}
