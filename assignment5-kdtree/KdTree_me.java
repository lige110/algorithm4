/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class KdTree_me {

    private static class Node {
        private Point2D p;
        private final RectHV rect;
        private Node lb;
        private Node rt;
        private final boolean isVertical;

        public Node(Point2D that, boolean isVertical, RectHV rect) {
            this.p = that;
            this.isVertical = isVertical;
            this.rect = rect;

        }


        private int compare(Point2D queryPoint) {
            return isVertical ? Point2D.X_ORDER.compare(queryPoint, this.p) :
                   Point2D.Y_ORDER.compare(queryPoint, this.p);
        }

        private int compare(RectHV queryRect) {
            return isVertical ? Double.compare(queryRect.xmax(), this.p.x()) :
                   Double.compare(queryRect.ymax(), this.p.y());
        }
    }


    private Node root;
    private int size;

    public KdTree_me() {
        size = 0;
    }

    public boolean isEmpty() {
        return this.root == null;
    }


    public int size() {
        return this.size;
    }


    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("called insert() with an empty key");
        if (!contains(p)) {
            root = insert(root, null, true, p);


            size++;
        }


    }

    private Node insert(Node node, Node pre, boolean isVertical, Point2D p) {
        if (node == null) {
            RectHV rect;
            if (pre == null) {
                rect = new RectHV(p.x(), 0, p.x(), 1);
                return new Node(p, isVertical, rect);
            }


            if (isVertical) {
                if (p.y() > pre.p.y()) rect = new RectHV(p.x(), pre.p.y(), p.x(), 1);
                else rect = new RectHV(p.x(), 0, p.x(), pre.p.y());
            }
            else {
                if (p.x() > pre.p.x()) rect = new RectHV(pre.p.x(), p.y(), 1, p.y());
                else rect = new RectHV(0, p.y(), pre.p.x(), p.y());
            }

            return new Node(p, isVertical, rect);
        }

        if (node.compare(p) < 0) node.lb = insert(node.lb, node, !isVertical, p);
        else node.rt = insert(node.rt, node, !isVertical, p);


        return node;

    }


    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("called contains() with an empty key");

        return contains(root, p);
    }

    private boolean contains(Node x, Point2D p) {
        if (x == null) return false;

        if (x.p.equals(p)) return true;

        if (x.compare(p) < 0) return contains(x.lb, p);
        else return contains(x.rt, p);


    }


    public void draw() {
        draw(root);

    }

    /***
     *  Use StdDraw.setPenColor(StdDraw.BLACK) and StdDraw.setPenRadius(0.01)
     *  before before drawing the points;
     *  use StdDraw.setPenColor(StdDraw.RED) or StdDraw.setPenColor(StdDraw.BLUE)
     *  and StdDraw.setPenRadius() before drawing the splitting lines.
     * @param x
     */
    private void draw(Node x) {
        if (x == null) return;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        x.p.draw();

        StdDraw.setPenColor(x.isVertical ? StdDraw.RED : StdDraw.BLUE);
        StdDraw.setPenRadius();
        x.rect.draw();
        draw(x.lb);
        draw(x.rt);

    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("called range() with an empty key");
        if (root == null) return null;

        Queue<Point2D> q = new Queue<>();

        range(root, rect, q);

        return q;
    }

    private void range(Node node, RectHV rect, Queue<Point2D> q) {
        if (node == null) return;
        if (rect.contains(node.p)) {
            q.enqueue(node.p);
        }
        if (rect.intersects(node.rect)) {
            range(node.lb, rect, q);
            range(node.rt, rect, q);
        }
        else if (node.compare(rect) < 0) range(node.lb, rect, q);
        else range(node.rt, rect, q);


    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("called nearest() with an empty key");
        if (root == null) return null;

        Point2D nearest = nearest(root, p, root.p);


        return nearest;

    }

    private Point2D nearest(Node x, Point2D p, Point2D nearest) {
        if (x == null) return nearest;
        // StdOut.println(x.p);
        if (x.p.distanceSquaredTo(p) < nearest.distanceSquaredTo(p)) {
            // StdOut.println(nearest);
            // StdOut.println(x.p.distanceSquaredTo(p));
            nearest = x.p;
            // StdOut.println(nearest);
        }


        if (x.compare(p) < 0) {
            nearest = nearest(x.lb, p, nearest);
            if (nearest.distanceSquaredTo(p) > x.rect.distanceSquaredTo(p)) {
                nearest = nearest(x.rt, p, nearest);
            }

        }
        else {
            nearest = nearest(x.rt, p, nearest);
            if (nearest.distanceSquaredTo(p) > x.rect.distanceSquaredTo(p)) {
                nearest = nearest(x.lb, p, nearest);
            }
        }
        return nearest;


    }


    public static void main(String[] args) {
        KdTree_me tree = new KdTree_me();
        Point2D p1 = new Point2D(0.5, 0.5);
        Point2D p2 = new Point2D(0.1, 0.1);
        Point2D p3 = new Point2D(0.2, 0.8);
        Point2D p4 = new Point2D(0.7, 0.4);
        Point2D p5 = new Point2D(0.9, 0.9);
        Point2D p6 = new Point2D(1, 1);

        tree.insert(p1);
        tree.insert(p2);
        tree.insert(p3);
        tree.insert(p4);
        tree.insert(p5);
        // tree.insert(p6);

        StdOut.println(tree.nearest(p6));

    }
}
