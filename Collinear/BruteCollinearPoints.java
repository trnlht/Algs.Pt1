/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints
{
    private int segmentsNum;

    private ArrayList<LineSegment> segmentsArrList;


    public BruteCollinearPoints(Point[] points)
    {
        if (!checkPoints(points))
            throw new IllegalArgumentException();

        segmentsNum = 0;

        segmentsArrList = new ArrayList<>();

        for (int i = 0; i < points.length; i++)
            for (int j = i + 1; j < points.length; j++)
                for (int k = j + 1; k < points.length; k++)
                    for (int m = k + 1; m < points.length; m++)
                    {
                        double slopeIJ = points[i].slopeTo(points[j]);
                        double slopeIK = points[i].slopeTo(points[k]);
                        double slopeIM = points[i].slopeTo(points[m]);

                        if (slopeIJ == slopeIK && slopeIK == slopeIM)
                        {
                            Point[] segment = { points[i], points[j], points[k], points[m] };
                            Arrays.sort(segment);
                            LineSegment ls = new LineSegment(segment[0], segment[3]);

                            segmentsArrList.add(ls);

                            segmentsNum++;
                        }

                    }
    }

    private boolean checkPoints(Point[] points)
    {
        if (points == null)
            return false;

        for (int i = 0; i < points.length; i++)
            if (points[i] == null)
                return false;

        Point[] cpoints = Arrays.copyOf(points, points.length);

        Arrays.sort(cpoints);

        for (int i = 0; i < cpoints.length - 1; i++)
        {
            if (cpoints[i].compareTo(cpoints[i + 1]) == 0)
                return false;
        }

        return true;
    }

    public int numberOfSegments()
    {
        return segmentsNum;
    }

    public LineSegment[] segments()
    {
        LineSegment[] ret = new LineSegment[segmentsArrList.size()];
        return segmentsArrList.toArray(ret);
    }

    public static void main(String[] args)
    {
        Point[] points = {
                new Point(0, 0),
                new Point(0, 1),
                new Point(0, 2),
                new Point(0, 3),
                new Point(1, 0),
                new Point(1, 1),
                new Point(1, 2),
                new Point(1, 3)
        };

        BruteCollinearPoints bcp = new BruteCollinearPoints(points);

        System.out.println("Segments count: " + bcp.numberOfSegments());
        System.out.println("Segments:");
        LineSegment[] segs = bcp.segments();
        for (int i = 0; i < bcp.numberOfSegments(); i++)
            System.out.println(segs[i].toString());

    }
}
