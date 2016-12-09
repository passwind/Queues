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
            catch (Exception e)
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
