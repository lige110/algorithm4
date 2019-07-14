/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {
    private Point[] points;
    private int numOfSeg = 0;
    private LineSegment[] linesegments;

    public BruteCollinearPoints(Point[] points) {
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


        Arrays.sort(points);
        int len = points.length;
        linesegments = new LineSegment[len];
        for (int i = 0; i < len - 3; i++) {
            for (int j = i + 1; j < len - 2; j++) {
                double slopeqp = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < len - 1; k++) {
                    double slopeqr = points[i].slopeTo(points[k]);
                    if (slopeqp == slopeqr) {
                        for (int l = k + 1; l < len; l++) {
                            double slopeqs = points[i].slopeTo(points[l]);
                            if (slopeqp == slopeqs) {
                                linesegments[numOfSeg] = new LineSegment(points[l], points[i]);
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
        for (Point item : points) {
            System.out.println(item);
        }


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
