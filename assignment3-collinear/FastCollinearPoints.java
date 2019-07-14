/* *****************************************************************************
 *  Name:Meng Li
 *  Date:26.04.2019
 *  Description:
 *  never change the iterator inside the for loop!!!!
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {
    private Point[] points;
    private Point[] temp;
    private int numOfSeg = 0;
    private LineSegment[] linesegments;
    private int buffer = 1;

    public FastCollinearPoints(Point[] points) {
        int flag = 1;
        if (points == null) throw new IllegalArgumentException("input can't be null");
        else {
            int len = points.length;
            for (int i = 0; i < len; i++) {
                if (points[i] == null) {
                    flag = 0;
                    break;
                }
            }
            if (flag == 0) throw new IllegalArgumentException("points array contains null element");
            else {
                for (int i = 0; i < len - 1; i++) {
                    for (int j = i + 1; j < len; j++) {
                        if (points[i].compareTo(points[j]) == 0) {
                            flag = 0;
                            break;
                        }

                    }
                }
                if (flag == 0)
                    throw new IllegalArgumentException("points array contains repeated points");
                else {
                    this.points = points;
                }
            }
        }

        Arrays.sort(
                points);                                                        // arrange the points in an increasing manner
        int len = points.length;
        linesegments = new LineSegment[len];
        temp = points.clone();
        for (int i = 0; i < len;
             i++) {                                                             // iterate all the points in the array
            Point p0 = points[i];
            Arrays.sort(temp, p0.slopeOrder());
            Point endp = temp[1];
            double k1 = p0.slopeTo(endp);
            buffer = 1;

            for (int j = 2; j < len; j++) {
                buffer++;
                if (p0.slopeTo(temp[j]) != k1) {
                    if (buffer > 3) {
                        // Arrays.sort(temp, j - buffer + 1, j - 1, p0.slopeOrder());
                        // if (p0 != temp[j - buffer + 1]) break;
                        linesegments[numOfSeg] = new LineSegment(p0, endp);
                        numOfSeg++;
                    }
                    buffer = 1;
                    endp = temp[j];
                    k1 = p0.slopeTo(endp);
                    continue;
                }
                else {
                    if (endp.compareTo(temp[j]) < 0) {
                        endp = temp[j];
                    }
                    if (j == len - 1) {
                        if (buffer > 3) {
                            Arrays.sort(temp, j - buffer + 1, j - 1, p0.slopeOrder());
                            if (p0 != temp[j - buffer + 1]) break;
                            linesegments[numOfSeg] = new LineSegment(p0, endp);
                            numOfSeg++;
                        }
                    }

                }


                // StdOut.println(end);

            }


        }


    }


    public int numberOfSegments() {
        return numOfSeg;
    }

    public LineSegment[] segments() {
        LineSegment[] t = new LineSegment[numOfSeg];
        for (int i = 0; i < numOfSeg; i++) {
            t[i] = linesegments[i];
        }

        return t;
    }


    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        StdOut.println(collinear.numberOfSegments());
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

    }
}
