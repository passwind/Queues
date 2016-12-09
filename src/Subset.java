/******************************************************************************
 *  Author: Zhu Yu
 *  Date: 2016-12-09
 *  Purpose:      Subset client. Write a client program Subset.java that 
 *                  takes a command-line integer k; reads in a sequence 
 *                  of N strings from standard input using StdIn.readString(); 
 *                  and prints out exactly k of them, uniformly at random. 
 *                  Each item from the sequence can be printed out at most once. 
 *                  You may assume that 0 ≤ k ≤ n, where n is the number of 
 *                  string on standard input. 
 *  Compilation:  javac Subset.java
 *  Execution:    echo A B C D E F G H I | java Subset 3 
 *  Dependencies: RandomizedQueue.java
 *
 ******************************************************************************/

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset
{

    public static void main(String[] args)
    {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (true) 
        {
            try
            {
                String s = StdIn.readString();
                rq.enqueue(s);
            }
            catch (NoSuchElementException e)
            {
                break;
            }
        }
        
        if (args.length != 1) throw new IllegalArgumentException();
        
        int k = Integer.parseInt(args[0]);
        
        if (k < 0 || k > rq.size()) throw new IllegalArgumentException();
        
        while (k > 0)
        {
            String s = rq.dequeue();
            StdOut.println(s);
            k--;
        }
    }

}
