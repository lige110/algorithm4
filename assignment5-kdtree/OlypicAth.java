/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class OlypicAth {


    @Override
    public int hashCode() {
        return 0;
    }

    // @Override


    public static void main(String[] args) {
        OlypicAth a1 = new OlypicAth();
        OlypicAth a2 = a1;
        OlypicAth a3 = new OlypicAth();
        StdOut.println(a1.equals(a3));
    }
}
