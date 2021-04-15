/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;

public class BruteCollinearPoints
{
    private int segmentsNum;

    public BruteCollinearPoints(Point[] points)
    {
        segmentsNum = 0;

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
                            LineSegment ls = new LineSegment(points[0], points[3]);

                            segmentsNum++;
                        }

                    }
    }

    public int numberOfSegments()
    {

    }

    public LineSegment[] segments()
    {

    }

    public static void main(String[] args)
    {

    }
}
