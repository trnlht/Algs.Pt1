import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>
{
    private int size;
    private int tail;

    private Item[] items;

    // construct an empty randomized queue
    public RandomizedQueue()
    {
        size = 0;
        tail = 0;
        items = (Item[]) new Object[1];
    }

    private void resize(int newCapacity)
    {
        Item[] newArray = (Item[]) new Object[newCapacity];
        int j = 0;
        for (int i = 0; i < tail; i++)
            if (items[i] != null)
                newArray[j++] = items[i];

        tail = size;
        items = newArray;
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

    private int getRandomIndex()
    {
        int randomIndex = StdRandom.uniform(tail);

        while (items[randomIndex] == null)
            randomIndex = StdRandom.uniform(tail);

        return randomIndex;
    }

    // remove and return a random item
    public Item dequeue()
    {
        if (size == 0)
            throw new NoSuchElementException();

        if (size == items.length / 4)
            resize(items.length / 2);

        int randomIndex = getRandomIndex();

        Item item = items[randomIndex];

        items[randomIndex] = null;

        size--;

        return item;

    }

    // return a random item (but do not remove it)
    public Item sample()
    {
        if (size == 0)
            throw new NoSuchElementException();

        int randomIndex = getRandomIndex();

        return items[randomIndex];
    }

    private class RandomizedQueueIterator implements Iterator<Item>
    {
        private final Item[] iteratorItems;

        private int currentIndex;

        public RandomizedQueueIterator()
        {
            currentIndex = 0;

            iteratorItems = (Item[]) new Object[size];

            int j = 0;

            for (int i = 0; i < items.length; i++)
            {
                if (items[i] != null)
                    iteratorItems[j++] = items[i];
            }

            StdRandom.shuffle(iteratorItems);
        }

        public boolean hasNext()
        {
            return currentIndex != iteratorItems.length;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        public Item next()
        {
            if (currentIndex == iteratorItems.length)
                throw new NoSuchElementException();

            return iteratorItems[currentIndex++];
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        return new RandomizedQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();

        for (int i = 0; i < 10; i++)
            rq.enqueue(i);

        System.out.println("Rq size: " + rq.size());

        System.out.println("Iterating over items in queue:");
        for (int item : rq)
            System.out.print(item + " ");

        System.out.println("\nRq size: " + rq.size());

        System.out.println("Removing items from queue:");
        while (!rq.isEmpty())
            System.out.print(rq.dequeue() + " ");

        System.out.println("\nRq size: " + rq.size());

        System.out.println();
    }

}
