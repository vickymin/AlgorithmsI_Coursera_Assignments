import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int N;

    // construct an empty randomized queue
    public RandomizedQueue() {
      s = (Item[]) new Object[2];
      N = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
      return N == 0;
    }

    // return the number of items on the queue
    public int size() {
      return N;
    }

    // add the item
    public void enqueue(Item item) {
      if (item == null) {
        throw new NullPointerException();
      }
      if (N == s.length) {
        resize(2 * s.length);
      }
      s[N] = item;
      N++;
    }

    // remove and return a random item
    public Item dequeue() {
      if (isEmpty()) {
        throw new NoSuchElementException();
      }
      int n = StdRandom.uniform(N);
      Item i = s[n];
      if (n != N - 1) {
        s[n] = s[N - 1];
      }
      N--;
      if (N > 0 && N == s.length/4) {
        resize(s.length/2);
      }
      return i;
    }

    // return (but do not remove) a random item
    public Item sample() {
      if (isEmpty()) {
        throw new NoSuchElementException();
      }
      return s[StdRandom.uniform(N)];
    }

    private void resize(int capacity) {
      Item[] copy = (Item[]) new Object[capacity];
      for (int i = 0; i < N; i++) {
        copy[i] = s[i];
      }
       s = copy;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
      return new ArrayIterator();
    }
    private class ArrayIterator implements Iterator<Item> {
      private Item[] temp = (Item[]) new Object[s.length];
      private int i = N;
      public boolean hasNext() {
        return i > 0;
      }
      public void remove() {
        throw new UnsupportedOperationException();
      }
      public Item next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(i);
        Item item = temp[index];
        if (index != i - 1) {
          temp[index] = s[i - 1];
        }
        i--;
        return item;
      }
    }

    // unit testing
    public static void main(String[] args) {

    }
}
