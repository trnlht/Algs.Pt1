import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>
{
    private int size;
    private int tail;

    private Item items[];

    private void resize(int newCapacity)
    {
        Item[] newArray = (Item[]) new Object[newCapacity];
        for (int i = 0, j = 0; i < tail; i++)
            if (items[i] != null)
                newArray[j++] = items[i];

        tail = size;
        items = newArray;
    }

    // construct an empty randomized queue
    public RandomizedQueue()
    {
        size = 0;
        tail = 0;
        items = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty()
    {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size()
    {
        return size;
    }

    // add the item
    public void enqueue(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException();

        if (tail == items.length)
            resize(2 * items.length);

        items[tail++] = item;

        size++;
    }

    // remove and return a random item
    public Item dequeue()
    {
        if (size == 0)
            throw new NoSuchElementException();
        

        while (true)
        {
            int randomIndex = StdRandom.uniform(1, tail);
            if (items[randomIndex] != null)
                break;
        }


        size--;
    }

    // return a random item (but do not remove it)
    public Item sample()
    {
        if (size == 0)
            throw new NoSuchElementException();
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {

    }

    // unit testing (required)
    public static void main(String[] args)
    {

    }

}
