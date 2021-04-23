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

    ArrayList<LineSegment> segmentsArrList;

    public FastCollinearPoints(
            Point[] points)     // finds all line segments containing 4 or more points
    {
        if (!Point.checkPoints(points))
            throw new IllegalArgumentException();

        segmentsCnt = 0;

        segmentsArrList = new ArrayList<>();

        for (int i = 0; i < points.length - 1; i++)
        {
            Arrays.sort(points, i + 1, points.length,
                        points[i].slopeOrder()/*Comparator for point i*/);

            int j = i + 1;
            //Проверяем, что три или более точек имеют одинаковый наклон с i-й точкой
            while (j < points.length)
            {
                int cpCnt = 0;
                int first = j;
                double curSlope = points[i].slopeTo(points[first]);

                while (j < points.length && points[i].slopeTo(points[j]) == curSlope)
                {
                    cpCnt++;
                    j++;
                }

                if (cpCnt >= 3)
                {
                    //Добавляем сегмент [first,j)
                    Point[] segment = Arrays.copyOfRange(points, first, j);
                    Arrays.sort(segment);
                    LineSegment ls = new LineSegment(points[i], segment[segment.length - 1]);

                    segmentsArrList.add(ls);
                    segmentsCnt++;
                }
            }

        }


    }


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
                new Point(0, 0),
                new Point(0, 1),
                new Point(0, 2),
                new Point(0, 3),
                new Point(1, 0),
                new Point(1, 1),
                new Point(1, 2),
                new Point(1, 3)
        };

        FastCollinearPoints fcp = new FastCollinearPoints(points);

        System.out.println("Segments count: " + fcp.numberOfSegments());
        System.out.println("Segments:");
        LineSegment[] segs = fcp.segments();
        for (int i = 0; i < fcp.numberOfSegments(); i++)
            System.out.println(segs[i].toString());
    }
}
