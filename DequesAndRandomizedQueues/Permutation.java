import java.util.Scanner;

public class Permutation
{
    public static void main(String[] args)
    {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> rq = new RandomizedQueue<>();

        Scanner in = new Scanner(System.in, "UTF-8");

        while (in.hasNext())
            rq.enqueue(in.next());


        while (k > 0)
        {
            System.out.println(rq.dequeue());
            k--;
        }

    }
}
