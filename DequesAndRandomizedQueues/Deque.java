import java.util.Iterator;


public class Deque<Item> implements Iterable<Item>
{
    class Node<Item>
    {

        public Node(Item item, Node next, Node prev)
        {
            this.next = next;
            this.item = item;
            this.prev = prev;
        }

        Node next;
        Node prev;

        Item item;
    }

    private class DequeIterator implements Iterator<Item>
    {
        private Node<Item> current = first;

        public boolean hasNext()
        {
            return current != null;
        }

        public void remove()
        {

        }

        public Item next()
        {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private Node<Item> first;

    private Node<Item> last;

    private int size;

    private void addInitialNode(Item item)
    {
        first = new Node<Item>(item, null, null);
        last = first;
    }

    private void removeSingleNode()
    {
        first = null;
        last = null;
    }

    // construct an empty deque
    public Deque()
    {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty()
    {
        return size == 0;
    }

    // return the number of items on the deque
    public int size()
    {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException();

        if (size == 0)
            addInitialNode(item);
        else
        {
            Node newNode = new Node<Item>(item, first, null);
            first.prev = newNode;
            first = newNode;
        }

        size++;
    }

    // add the item to the back
    public void addLast(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException();

        if (size == 0)
            addInitialNode(item);
        else
        {
            Node newNode = new Node<Item>(item, null, last);
            last.next = newNode;
            last = newNode;
        }

        size++;
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if (size == 0)
            throw new java.util.NoSuchElementException();

        Item firstItem = first.item;

        if (size == 1)
            removeSingleNode();
        else
        {
            first.next.prev = null;
            first = first.next;
        }

        size--;

        return firstItem;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
        if (size == 0)
            throw new java.util.NoSuchElementException();

        Item lastItem = last.item;

        if (size == 1)
            removeSingleNode();
        else
        {
            last = last.prev;
            last.next = null;
        }

        size--;

        return lastItem;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        Deque<Integer> d = new Deque<Integer>();
        d.addFirst(1);
        d.addLast(2);
        d.addLast(3);

        for (int i : d)
            System.out.print(i + " ");

        System.out.println("\nsize = " + d.size());

        int x = d.removeFirst();
        System.out.println(x);

        x = d.removeLast();
        System.out.println(x);

        for (int i : d)
            System.out.print(i + " ");

        System.out.println();
        System.out.println(d.isEmpty());

        d.removeFirst();

        System.out.println(d.isEmpty());
    }

}
