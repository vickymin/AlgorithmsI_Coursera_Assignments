import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int length;

    private class Node {
      private Item item;
      private Node previous;
      private Node next;
    }

    // construct an empty deque
    public Deque() {
      first = null;
      last = null;
      length = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
      return length == 0;
    }

    // return the number of items on the deque
    public int size() {
      return length;
    }

    // add the item to the front
    public void addFirst(Item item) {
      if (item == null) {
        throw new NullPointerException();
      }
      Node oldFirst = first;
      first = new Node();
      first.item = item;
      first.previous = null;
      if (isEmpty()) {
        last = first;
        first.next = oldFirst;
      } else {
        oldFirst.previous = first;
        first.next = oldFirst;
      }
      length++;
    }

    // add the item to the end
    public void addLast(Item item) {
      if (item == null) {
        throw new NullPointerException();
      }
      Node oldLast = last;
      last = new Node();
      last.item = item;
      last.next = null;
      if (isEmpty()) {
        first = last;
        first.previous = null;
      } else {
        oldLast.next = last;
        last.previous = oldLast;
      }
      length++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
      if (isEmpty()) {
        throw new NoSuchElementException();
      }
      Item item = first.item;
      first = first.next;
      length--;
      if (isEmpty()) {
        first = null;
        last = null;
      } else {
        first.previous = null;
      }
      return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
      if (isEmpty()) {
        throw new NoSuchElementException();
      }
      Item item = last.item;
      last = last.previous;
      length--;
      if (isEmpty()) {
        first = null;
        last = null;
      } else {
        last.next = null;
      }
      return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
      return new ListIterator();
    }
    private class ListIterator implements Iterator<Item> {
      private Node current = first;
      public boolean hasNext() {
        return current != null;
      }
      public void remove() {
        throw new UnsupportedOperationException();
      }
      public Item next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        Item item = current.item;
        current = current.next;
        return item;
      }
    }

    // unit testing
    public static void main(String[] args) {

    }
}
