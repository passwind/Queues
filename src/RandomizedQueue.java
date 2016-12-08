import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int N = 0;
    
    // construct an empty randomized queue
    public RandomizedQueue()     
    {
        items = (Item[]) new Object[1];
    }
    
    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++)
            copy[i] = items[i];
            
        items = copy;
    }
    
    // is the queue empty?
    public boolean isEmpty()  
    {
        return N == 0;
    }
    
    // return the number of items on the queue
    public int size() 
    {
        return N;
    }
    
    // add the item
    public void enqueue(Item item)   
    {
        if (item == null) throw new NullPointerException();
        if (N == items.length) resize(2 * items.length);
        items[N++] = item;
    }
    
    // remove and return a random item
    public Item dequeue()    
    {
        if (N == 0) throw new NoSuchElementException();
        int idx = StdRandom.uniform(0, N);
        Item item = items[idx];
        for (int i = idx; i < N-1; i++)
            items[i] = items[i+1];
        if (N>0 && N == items.length/4) resize(items.length/2);
        N--;
        return item;
    }
    
    // return (but do not remove) a random item
    public Item sample()       
    {
        if (N == 0) throw new NoSuchElementException();
        int idx = StdRandom.uniform(0, N);
        Item item = items[idx];
        return item;
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator()      
    {
        return new QueueIterator();
    }
    
    private class QueueIterator implements Iterator<Item>
    {
        private int current = -1;
        private Item[] iteratorItems;
        
        public QueueIterator()
        {
            iteratorItems = (Item[]) new Object[items.length];
            int[] indexes = new int[items.length];
            for (int i = 0; i < items.length; i++) indexes[i] = i;
            
            for (int i = items.length; i > 0; i--)
            {
                int idx = StdRandom.uniform(0, i);
                int itemIdx = indexes[idx];
                for (int j = idx; j < i-1; j++) indexes[j] = indexes[j+1];
                
                iteratorItems[i-1] = items[itemIdx];
            }
            
            if (iteratorItems.length > 0) current = 0;
        }
        
        public boolean hasNext() { return current != -1; }
        public void remove() 
        {
            throw new UnsupportedOperationException();
        }
        
        public Item next()
        {
            if (current == -1) {
                throw new NoSuchElementException();
            }
            Item item = iteratorItems[current];
            current ++;
            if (current == iteratorItems.length) current = -1;
            return item;
        }
    }
    
    // unit testing
    public static void main(String[] args)   
    {
        RandomizedQueue rq = new RandomizedQueue<String>();
        System.out.println("initial queue is empty: " + rq.isEmpty());
        System.out.println("size of initial queue is 0: " + rq.size());
        
        try 
        {
            rq.enqueue(null);
        }
        catch (NullPointerException e)
        {
            System.out.println("OK: enqueue - " + e.getClass());
        }
        
        try 
        {
            rq.dequeue();
        }
        catch (NoSuchElementException e)
        {
            System.out.println("OK: dequeue - " + e.getClass());
        }
        
        try 
        {
            rq.sample();
        }
        catch (NoSuchElementException e)
        {
            System.out.println("OK: sample - " + e.getClass());
        }
        
        StdOut.println("test enqueue");
        
        rq.enqueue("hello");
        rq.enqueue(",");
        rq.enqueue("world");
        rq.enqueue("!");
        
        StdOut.println("first iterating: ");
        Iterator<String> iter1 = rq.iterator();
        while (iter1.hasNext())
        {
            String s = iter1.next();
            StdOut.println(s);
        }

        StdOut.println("second iterating: ");
        Iterator<String> iter2 = rq.iterator();
        while (iter2.hasNext())
        {
            String s = iter2.next();
            StdOut.println(s);
        }
        
        try 
        {
            iter2.next();
        }
        catch (NoSuchElementException e)
        {
            System.out.println("OK: next - " + e.getClass());
        }
        
        try 
        {
            iter1.remove();
        }
        catch (UnsupportedOperationException e)
        {
            System.out.println("OK: iter.remove - " + e.getClass());
        }
        
        StdOut.println("test dequeue");
        
        String s1 = (String) rq.dequeue();
        StdOut.println(s1);
        String s2 = (String) rq.dequeue();
        StdOut.println(s2);
        
        System.out.println("after remove 2 queue is empty: " + rq.isEmpty());
        System.out.println("size of after remove 2 is 0: " + rq.size());
        
        String s3 = (String) rq.dequeue();
        StdOut.println(s3);
        String s4 = (String) rq.dequeue();
        StdOut.println(s4);
        
        System.out.println("after remove 2 queue is empty: " + rq.isEmpty());
        System.out.println("size of after remove 2 is 0: " + rq.size());
    }

}
