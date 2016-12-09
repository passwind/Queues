/******************************************************************************
 *  Author: Zhu Yu
 *  Date: 2016-12-09
 *  Purpose:      A double-ended queue or deque (pronounced "deck") is 
 *                  a generalization of a stack and a queue that supports 
 *                  adding and removing items from either the front 
 *                  or the back of the data structure. 
 *  Compilation:  javac Deque.java
 *  Execution:    java Deque
 *  Dependencies: None
 *
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;


public class Deque<Item> implements Iterable<Item> 
{
    // first node
    private Node first = null;
    // last node
    private Node last = null;
    // size of items
    private int n = 0;
    
    // construct an empty deque
    public Deque() {
    }
    
    // node structure
    private class Node
    {
        private Item item;
        private Node prior;
        private Node next;
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
    
    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }
    
    // return the number of items on the deque
    public int size() 
    {
        return n;
    }
    
    // add the item to the front
    public void addFirst(Item item) 
    {
        Node node = makeNode(item);
        
        if (first == null)
        {
            first = node;
            last = node;
        }
        else 
        {
            Node oldFirst = first;
            first = node;
            first.next = oldFirst;
            oldFirst.prior = first;
        }
        
        n++;
    }
    
    // add the item to the end
    public void addLast(Item item) {
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
    
    // remove and return the item from the front
    public Item removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        
        Node oldFirst = first;
        first = first.next;
        if (first != null)
            first.prior = null;
        
        n--;
        
        if (n == 0) last = null;
        
        return oldFirst.item;
    }
    
    // remove and return the item from the end
    public Item removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        
        Node oldLast = last;
        last = last.prior;
        if (last != null)
            last.next = null;
        
        n--;
        
        if (n == 0) first = null;
        
        return oldLast.item;
    }
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() 
    { return new ListIterator(); }
    
    private class ListIterator implements Iterator<Item> 
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
    public static void main(String[] args) {
        Deque<String> dq = new Deque<String>();
        
        try 
        {
            dq.addFirst(null);
        }
        catch (NullPointerException e)
        {
            System.out.println("OK: addFirst - " + e.getClass());
        }
        
        try 
        {
            dq.addLast(null);
        }
        catch (NullPointerException e)
        {
            System.out.println("OK: addLast - " + e.getClass());
        }
        
        try 
        {
            dq.removeFirst();
        }
        catch (NoSuchElementException e)
        {
            System.out.println("OK: removeFirst - " + e.getClass());
        }
        
        try 
        {
            dq.removeLast();
        }
        catch (NoSuchElementException e)
        {
            System.out.println("OK: removeLast - " + e.getClass());
        }
        
        Iterator<String> iter = dq.iterator();
        
        try 
        {
            iter.remove();
        }
        catch (UnsupportedOperationException e)
        {
            System.out.println("OK: iter.remove - " + e.getClass());
        }
        
        try 
        {
            iter.next();
        }
        catch (NoSuchElementException e)
        {
            System.out.println("OK: iter.next - " + e.getClass());
        }
        
        dq.addFirst("hello");
        dq.addLast(",");
        dq.addFirst("world");
        dq.addLast("!");
        
        System.out.println("size should be 4, real is " + dq.size());
        
        iter = dq.iterator();
        while (iter.hasNext())
        {
            String s = iter.next();
            System.out.println(s + " ");
        }
        
        dq.removeFirst();
        dq.removeLast();
        
        System.out.println("after remove 2 size should be 2, real is " + dq.size());
        
        iter = dq.iterator();
        while (iter.hasNext())
        {
            String s = iter.next();
            System.out.println(s + " ");
        }
        
        dq.removeLast();
        dq.removeFirst();
        
        System.out.println("after remove 2 size should be 0, real is " + dq.size());
        
        System.out.println("deque should be empty. - " + dq.isEmpty());
        
        dq.addFirst("test");
        StdOut.println("size : " + dq.size());
        dq.removeLast();
        StdOut.println("size : " + dq.size());
        
        dq.addFirst("haha");
        dq.addFirst("hehe");
        String t = dq.removeLast();
        StdOut.println("should be haha: " + t);
    }

}
