import edu.princeton.cs.algs4.StdIn;

public class Permutation
{
    public static void main(String[] args)
    {
        int k = Integer.parseInt(args[0]);

        System.out.println("k = " + k);

        RandomizedQueue rq = new RandomizedQueue();

        while (!StdIn.isEmpty())
            rq.enqueue(StdIn.readString());

        while (!rq.isEmpty())
            System.out.println(rq.dequeue());

        //Scanner in = new Scanner(System.in);
        //in.next()

    }
}
