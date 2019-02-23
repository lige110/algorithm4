/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int numItem;                                 // number of elements in quene
    private Item[] array;                                // array of items


    /**
     * Initialize an empty randomizedquene
     */
    public RandomizedQueue() {
        numItem = 0;
        array = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return numItem == 0;
    }

    public int size() {
        return numItem;
    }

    private void resize(int capacity) {
        assert capacity >= numItem;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < numItem; i++) {
            temp[i] = array[i];
        }
        array = temp;

    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("the item should not be null!");
        if (numItem == array.length) resize(2 * array.length);
        array[numItem++] = item;
    }

    public Item dequeue() {
        if (numItem == 0) throw new NoSuchElementException("the quene is empty!");
        int index = StdRandom.uniform(numItem);
        Item item = array[index];
        for (int i = index; i < numItem - 1; i++) {
            array[i] = array[i + 1];
        }
        array[numItem - 1] = null;
        numItem--;
        if (numItem == array.length / 4) resize(array.length / 4);
        return item;


    }

    public Item sample() {
        if (numItem == 0) throw new NoSuchElementException("the quene is empty!");
        int index = StdRandom.uniform(numItem);
        Item item = array[index];
        return item;

    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();

    }

    private class ArrayIterator implements Iterator<Item> {
        private Item[] iterarray = (Item[]) new Object[numItem];
        private int i;

        public ArrayIterator() {
            for (int j = 0; j < numItem; j++) {
                iterarray[j] = array[j];
            }
            StdRandom.shuffle(iterarray);
            i = numItem - 1;
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("No More Elements in the Iterator!");
            return iterarray[i--];


        }

    }


    public static void main(String[] args) {
        RandomizedQueue<Integer> rquene = new RandomizedQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            rquene.enqueue(i);
        }
        StdOut.println(rquene.size());
        StdOut.println(rquene.sample());
        Iterator<Integer> iterator = rquene.iterator();
        while (iterator.hasNext()) {
            StdOut.println(iterator.next());
        }
        StdOut.println("Now for the dequeue");
        for (int i = 0; i < 10; i++) {
            StdOut.println(rquene.dequeue());
        }

    }
}
