/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {
    // private Point[] points;
    private int numOfSeg = 0;
    // private LineSegment[] linesegments;
    private final Stack<LineSegment> linesegments = new Stack<LineSegment>();

    public BruteCollinearPoints(Point[] plist) {
        int flag = 1;
        if (plist == null) throw new IllegalArgumentException("input can't be null");
        else {
            int len = plist.length;
            for (int i = 0; i < len; i++) {
                if (plist[i] == null) {
                    flag = 0;
                    break;
                }
            }
            if (flag == 0) throw new IllegalArgumentException("points array contains null element");
            else {
                for (int i = 0; i < len - 1; i++) {
                    for (int j = i + 1; j < len; j++) {
                        if (plist[i].compareTo(plist[j]) == 0) {
                            flag = 0;
                            break;
                        }

                    }
                }
                if (flag == 0)
                    throw new IllegalArgumentException("points array contains repeated points");
            }
        }

        /**
         *   arrange the points from down to the top
         */
        final Point[] points = plist.clone();

        Arrays.sort(points);

        int len = points.length;
        int i, j, k, l;

        for (i = 0; i < len - 3; i++) {
            for (j = i + 1; j < len - 2; j++) {
                double slopeqp = points[i].slopeTo(points[j]);
                for (k = j + 1; k < len - 1; k++) {
                    double slopeqr = points[i].slopeTo(points[k]);
                    if (slopeqp == slopeqr) {
                        for (l = k + 1; l < len; l++) {
                            double slopeqs = points[i].slopeTo(points[l]);
                            if (slopeqp == slopeqs) {
                                // linesegments[numOfSeg] = new LineSegment(points[l], points[i]);
                                LineSegment line = new LineSegment(points[l], points[i]);
                                linesegments.push(line);
                                numOfSeg++;
                            }

                        }
                    }
                }
            }

        }

    }

    public int numberOfSegments() {
        return numOfSeg;


    }

    public LineSegment[] segments() {
        LineSegment[] t = new LineSegment[numOfSeg];
        for (int i = 0; i < numOfSeg; i++) {
            // t[i] = linesegments[i];
            t[i] = linesegments.pop();
        }

        return t;

    }

    public static void main(String[] args) {
        // read the n points from a file


        // input50 has 5 collinear points!!!!! fk!


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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        StdOut.println(collinear.numOfSeg);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();


    }
}
