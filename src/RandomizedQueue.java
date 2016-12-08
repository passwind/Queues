import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // construct an empty randomized queue
    public RandomizedQueue()     
    {
        
    }
    
    private class Node 
    {
        
    }
    
    private Node makeNode(Item item)
    {
        if (item == null) throw new NullPointerException();
    }
    
    // is the queue empty?
    public boolean isEmpty()  
    {
        // TODO:
        return false;
    }
    
    // return the number of items on the queue
    public int size() 
    {
        // TODO:
        return 0;
    }
    
    // add the item
    public void enqueue(Item item)   
    {
        
    }
    
    // remove and return a random item
    public Item dequeue()    
    {
        return null;
    }
    
    // return (but do not remove) a random item
    public Item sample()       
    {
        return null;
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator()      
    {
        return new QueueIterator();
    }
    
    private class QueueIterator() implements iterator<Item>
    {
        private Node current = first;
        
        public boolean hasNext() { return current != null; }
        public void remove() 
        {
            throw new UnsupportedOperationException();
        }
        
        public Item next()
        {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    // unit testing
    public static void main(String[] args)   
    {
        
    }

}
