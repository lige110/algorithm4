/* *****************************************************************************
 *  Name:Meng Li
 *  Date:26.04.2019
 *  Description:
 *  never change the iterator inside the for loop!!!!
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {
    private final int segNum;

    private final Stack<LineSegment> linesegments = new Stack<LineSegment>();

    public FastCollinearPoints(Point[] plist) {
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

        // Arrays.sort(points);                        // arrange the points in an increasing manner
        final Point[] points = plist.clone();

        int len = points.length;
        // linesegments = new LineSegment[len];
        Point[] temp = points.clone();


        int first;
        int second;
        int count = 1;
        int numOfSeg = 0;
        for (first = 0; first < len; first++) {
            // set initial point
            Point p0 = points[first];
            count = 1;
            Arrays.sort(temp, p0.slopeOrder());
            for (second = 1; second < len; second++) {
                if (temp[second].slopeTo(p0) == temp[second - 1]
                        .slopeTo(p0)) {
                    count++;


                }
                else {
                    if (count > 2) {


                        Arrays.sort(temp, second - count, second);


                        if (temp[second - count].compareTo(p0) > 0) {
                            // linesegments[numOfSeg] = new LineSegment(p0, temp[second - 1]);
                            LineSegment line = new LineSegment(p0, temp[second - 1]);
                            linesegments.push(line);
                            numOfSeg++;
                        }
                    }

                    count = 1;
                }

            }

            // when the last element in slope sorted array meet the requirement
            if (count > 2) {
                Arrays.sort(temp, second - count, second);
                if (temp[second - count].compareTo(p0) > 0) {

                    // linesegments[numOfSeg] = new LineSegment(p0, temp[second - 1]);
                    LineSegment line = new LineSegment(p0, temp[second - 1]);
                    linesegments.push(line);
                    numOfSeg++;
                }

            }

        }
        segNum = numOfSeg;

    }


    public int numberOfSegments() {
        return segNum;
    }

    public LineSegment[] segments() {
        LineSegment[] t = new LineSegment[segNum];
        for (int i = 0; i < segNum; i++) {
            // t[i] = linesegments[i];
            t[i] = linesegments.pop();
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
