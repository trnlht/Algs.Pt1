/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints
{
    private int segmentsCnt;

    private ArrayList<LineSegment> segmentsArrList;


    public FastCollinearPoints(
            Point[] points)     // finds all line segments containing 4 or more points
    {
        if (!checkPoints(points))
            throw new IllegalArgumentException();

        segmentsCnt = 0;

        segmentsArrList = new ArrayList<>();

        for (int i = 0; i < points.length - 1; i++)
        {
            Arrays.sort(points, i + 1, points.length,
                        points[i].slopeOrder()/* Comparator for point i */);

            int j = i + 1;
            //Проверяем, что три или более точек имеют одинаковый наклон с i-й точкой
            while (j < points.length)
            {
                int cpCnt = 0;
                int first = j;
                double curSlope = points[i].slopeTo(points[first]);

                while (j < points.length)
                {
                    double slopeJ = points[i].slopeTo(points[j]);
                    if (slopeJ == curSlope)
                    {
                        cpCnt++;
                        j++;
                    }
                    else
                        break;
                }

                if (cpCnt >= 3)
                {
                    // TODO Сортировать все 4 точки (!!!)
                    // Добавляем сегмент [first,j)
                    // Point[] segment = Arrays.copyOfRange(points, first, j);
                    Point[] segment = new Point[j - first + 1];
                    segment[0] = points[i];
                    for (int k = 1; k < segment.length; k++)
                        segment[k] = points[first++];

                    Arrays.sort(segment);
                    // printSegmentPoints(points[i], segment);
                    LineSegment ls = new LineSegment(segment[0], segment[segment.length - 1]);

                    segmentsArrList.add(ls);
                    segmentsCnt++;
                }
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

    // private void printSegmentPoints(Point base, Point[] points)
    // {
    //     System.out.println("Segment found:");
    //     StringBuilder res = new StringBuilder(base.toString() + " ");
    //     for (int i = 0; i < points.length; i++)
    //         res.append(points[i].toString() + " ");
    //
    //     System.out.println(res.toString());
    // }

    public int numberOfSegments()        // the number of line segments
    {
        return segmentsCnt;
    }

    public LineSegment[] segments()                // the line segments
    {
        LineSegment[] ret = new LineSegment[segmentsArrList.size()];
        return segmentsArrList.toArray(ret);
    }

    public static void main(String[] args)
    {
        Point[] points = {
                new Point(0, 1),
                new Point(0, 0),
                new Point(0, 2),
                new Point(0, 3),
                new Point(1, 0),
                new Point(1, 1),
                new Point(1, 2),
                new Point(1, 3),
                new Point(5, 0),
                new Point(5, 1),
                new Point(5, 2),
                new Point(5, 3)
        };

        FastCollinearPoints fcp = new FastCollinearPoints(points);

        System.out.println("Segments count: " + fcp.numberOfSegments());
        System.out.println("Segments:");
        LineSegment[] segs = fcp.segments();
        for (int i = 0; i < fcp.numberOfSegments(); i++)
            System.out.println(segs[i].toString());
    }
}
