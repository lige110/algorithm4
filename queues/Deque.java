/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int num;
    private Node first;
    private Node last;

    private class Node {
        private Item item;
        private Node next;
        private Node previous;

    }


    public Deque() {
        first = null;
        last = null;
        num = 0;

    }

    public boolean isEmpty() {
        return num == 0;
    }

    public int size() {
        return num;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("input should not be null");
        Node oldfirst = first;
        first = new Node();
        if (num
                > 0) {                                                          // only when there is already a node,
            oldfirst.previous
                    = first;                                          // it should point to the new node
        }
        first.next = oldfirst;
        first.item = item;
        first.previous = null;
        num++;
        if (num == 1) {
            last = first;
        }

    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("input should not be null");
        Node oldlast = last;
        last = new Node();
        if (num
                > 0) {                                                         // only when there is already a node,
            oldlast.next
                    = last;                                                // it should point to the new node
        }
        last.previous = oldlast;
        last.item = item;
        last.next = null;
        num++;
        if (num == 1) first = last;

    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("the deque is already empty!");
        Item item;
        if (first
                .equals(last)) {                                                // if we have only one item left
            item
                    = removelastitem();                                            // we use another way to remove it
            num--;
        }
        else {
            item = first.item;
            Node oldfirst = first;
            first = oldfirst.next;
            num--;
            if (!isEmpty()) first.previous = null;
        }

        return item;

    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("the deque is already empty!");
        Item item;
        if (first
                .equals(last)) {                                               // if we have only one item left
            item
                    = removelastitem();                                            // we use another way to remove it
            num--;
        }
        else {
            item = last.item;
            Node oldlast = last;
            last = oldlast.previous;
            num--;
            if (!isEmpty()) last.next = null;
        }
        return item;
    }

    /**
     * for situation when there is only one element left in the deque
     */
    private Item removelastitem() {
        Item item = first.item;
        first = null;
        last = null;
        return item;
    }


    /**
     * to build an Iterator
     */
    public Iterator<Item> iterator() {
        return new Myinterator();
    }

    private class Myinterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("not supported");
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("no elements");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<Integer>();
        // Test 1
        for (int i = 0; i < 6; i++) {
            dq.addFirst(i);
        }
        while (!dq.isEmpty()) {
            StdOut.println(dq.removeLast());
        }
        // Test 2
        for (int i = 0; i < 6; i++) {
            dq.addFirst(i);
        }
        while (!dq.isEmpty()) {
            StdOut.println(dq.removeFirst());
        }
        // Test 3
        for (int i = 0; i < 6; i++) {
            dq.addLast(i);
        }
        while (!dq.isEmpty()) {
            StdOut.println(dq.removeLast());
        }
        // Test 4
        for (int i = 0; i < 6; i++) {
            dq.addLast(i);
        }
        while (!dq.isEmpty()) {
            StdOut.println(dq.removeFirst());
        }


    }
}
