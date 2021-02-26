public class Deque<Item> implements Iterable<Item>
{
    class Node<Item>
    {

        public Node(Item item, Node next)
        {
            this.next = next;
            this.item = item;
        }

        Node next;

        Item item;
    }

    private Node<Item> first;

    private Node<Item> last;

    private int size;

    private void addInitialNode(Item item)
    {
        first = new Node<Item>(item, null);
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
            newNode = new Node<Item>(item, first);
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
            newNode = new Node<Item>(item, null);
            last.next = newNode;
            last = newNode;
        }

        size++;
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if (size == 0)
            return new java.util.NoSuchElementException();

        Item firstItem = first.item;

        if (size == 1)
            removeSingleNode();
        else
            first = first.next;

        size--;

        return firstItem;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
        if (size == 0)
            return new java.util.NoSuchElementException();

        Item lastItem = last.item;

        if (size == 1)
            removeSingleNode();
        else
        {

        }

        size--;

        return lastItem;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()

    // unit testing (required)
    public static void main(String[] args)

}
