/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class test {
    public int add(int x, int y) {
        return x + y;
    }

    public static void main(String[] args) {
        int[][] a = new int[2][3];
        a[0][0] = 1;
        a[0][1] = 1;
        a[1][0] = 1;
        a[1][1] = 1;
        a[0][2] = 1;
        a[1][2] = 1;
        System.out.println(a.length);
        int x = 1, y = 1;
        test t = new test();
        int i = t.add(x + 1, y);
        System.out.print(i);

    }
}
