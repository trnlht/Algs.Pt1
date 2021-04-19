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

    ArrayList<LineSegment> segmentsArr;

    public BruteCollinearPoints(Point[] points)
    {
        if (points == null)
            throw new IllegalArgumentException();

        segmentsNum = 0;

        segmentsArr = new ArrayList<>();

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

                            segmentsArr.add(ls);

                            segmentsNum++;
                        }

                    }
    }

    public int numberOfSegments()
    {
        return segmentsNum;
    }

    public LineSegment[] segments()
    {
        LineSegment[] ret = new LineSegment[segmentsArr.size()];
        return segmentsArr.toArray(ret);
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
