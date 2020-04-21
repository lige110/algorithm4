/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:represents a set of points in the unit square.
 *              Implement the following API by using a red–black BST
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.Iterator;

public class PointSET {
    private final SET<Point2D> set;

    public PointSET() {
        set = new SET<Point2D>();
    }


    /**
     * Returns true if this set is empty.
     *
     * @return {@code true} if this set is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return this.set.isEmpty();

    }

    /**
     * Returns the number of keys in this set.
     *
     * @return the number of keys in this set
     */
    public int size() {
        return set.size();
    }

    public void insert(Point2D p) {
        set.add(p);
    }

    /**
     * Returns true if this set contains the given key.
     *
     * @param p the key
     * @return {@code true} if this set contains {@code key}; {@code false} otherwise
     * @throws IllegalArgumentException if {@code p} is {@code null}
     */
    public boolean contains(Point2D p) {
        return set.contains(p);
    }

    public void draw() {
        if (set.isEmpty()) throw new NullPointerException("called draw() with an empty set");
        Iterator<Point2D> it = set.iterator();

        while (it.hasNext()) {
            it.next().draw();
        }

    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("called range() with a null key");
        if (set.isEmpty()) return null;

        SET<Point2D> result = new SET<>();
        Iterator<Point2D> it = set.iterator();
        while (it.hasNext()) {
            Point2D temp = it.next();
            if (rect.contains(temp)) {
                result.add(temp);
            }

        }
        return result;

    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("called nearst() with a null key");
        if (set.isEmpty()) return null;

        Iterator<Point2D> it = set.iterator();
        // initiate the result
        Point2D nearest = it.next();
        double minDistance = p.distanceSquaredTo(nearest);
        while (it.hasNext()) {
            Point2D temp = it.next();
            if (p.distanceSquaredTo(temp) < minDistance) {

                minDistance = p.distanceSquaredTo(temp);
                nearest = temp;
            }


        }

        return nearest;

    }

    public static void main(String[] args) {

    }
}
