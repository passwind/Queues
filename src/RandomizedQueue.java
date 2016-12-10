/******************************************************************************
 *  Author: Zhu Yu
 *  Date: 2016-12-09
 *  Purpose:      A randomized queue is similar to a stack or queue, 
 *                  except that the item removed is chosen uniformly 
 *                  at random from items in the data structure. 
 *  Compilation:  javac -Xlint:unchecked RandomizedQueue.java
 *  Execution:    java RandomizedQueue
 *  Dependencies: None
 *
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // linked-list: first node & last node
    private Node first = null;
    private Node last = null;
    // space of data storage
    private int n = 0;
    
    // construct an empty randomized queue
    public RandomizedQueue()     
    {
    }
    
    // node structure
    private class Node
    {
        private Item item;
        private Node prior;
        private Node next;
    }
    
    // is the queue empty?
    public boolean isEmpty()  
    {
        return n == 0;
    }
    
    // return the number of items on the queue
    public int size() 
    {
        return n;
    }
    
    // create node with data
    private Node makeNode(Item item)
    {
        if (item == null) 
        {
            throw new NullPointerException();
        }
        
        Node node = new Node();
        node.item = item;
        node.prior = null;
        node.next = null;
        
        return node;
    }
    
    // add the item
    public void enqueue(Item item)   
    {
        Node node = makeNode(item);
        
        if (last == null) 
        {
            first = node;
            last = node;
        }
        else
        {
            last.next = node;
            node.prior = last;
            last = node;
        }
        
        n++;
    }
    
    private Node fetchNodeByIndex(int idx)
    {
        int i = 0;
        Node current = first;
        while (i < idx && current != null)
        {
            current = current.next;
            i++;
        }
        
        return current;
    }
    
    // remove and return a random item
    public Item dequeue()    
    {
        if (n == 0) throw new NoSuchElementException();
        int idx = StdRandom.uniform(0, n);
        Node current = fetchNodeByIndex(idx);
        
        if (current == first)
        {
            first = first.next;
        }
        else
        {
            if (current.prior != null) current.prior.next = current.next;
            if (current.next != null) current.next.prior = current.prior;
        }
        n--;
        return current.item;
    }
    
    // return (but do not remove) a random item
    public Item sample()       
    {
        if (n == 0) throw new NoSuchElementException();
        int idx = StdRandom.uniform(0, n);
        Node current = fetchNodeByIndex(idx);
        return current.item;
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator()      
    {
        return new QueueIterator();
    }
    
    // iterator class definition
    private class QueueIterator implements Iterator<Item>
    {
        private int current = -1;
        private Item[] iteratorItems;
        
        public QueueIterator()
        {
            iteratorItems = (Item[]) new Object[n];
            int[] indexes = new int[n];
            for (int i = 0; i < n; i++) indexes[i] = i;
            
            for (int i = n; i > 0; i--)
            {
                int idx = StdRandom.uniform(0, i);
                int itemIdx = indexes[idx];
                for (int j = idx; j < i-1; j++) indexes[j] = indexes[j+1];
                Node node = fetchNodeByIndex(itemIdx);
                
                iteratorItems[i-1] = node.item;
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
            current++;
            if (current == iteratorItems.length) current = -1;
            return item;
        }
    }
    
    // unit testing
    public static void main(String[] args)   
    {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
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
        
        String s1 = rq.dequeue();
        StdOut.println(s1);
        String s2 = rq.dequeue();
        StdOut.println(s2);
        
        System.out.println("after remove 2 queue is empty: " + rq.isEmpty());
        System.out.println("size of after remove 2 is 0: " + rq.size());
        
        String s3 = rq.dequeue();
        StdOut.println(s3);
        String s4 = rq.dequeue();
        StdOut.println(s4);
        
        System.out.println("after remove 2 queue is empty: " + rq.isEmpty());
        System.out.println("size of after remove 2 is 0: " + rq.size());
    }

}
