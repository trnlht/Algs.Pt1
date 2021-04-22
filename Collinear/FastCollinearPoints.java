/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints
{
    private int segmentsNum;

    ArrayList<LineSegment> segmentsArr;

    public FastCollinearPoints(
            Point[] points)     // finds all line segments containing 4 or more points
    {
        if (!checkPoints(points))
            throw new IllegalArgumentException();

        segmentsNum = 0;

        segmentsArr = new ArrayList<>();

        for (int i = 0; i < points.length - 1; i++)
        {
            Arrays.sort(points, i + 1, points.length,
                        points[i].slopeOrder()/*Comparator for point i*/);

            int first = i + 1;
            int cpCnt = 0;
            int j = i + 1;
            double curSlope = points[i].slopeTo(points[first]);
            //Проверяем, что три или более точек имеют одинаковый наклон с i-й точкой
            while (j < points.length)
            {
                while (j < points.length && points[i].slopeTo(points[j]) == curSlope)
                {
                    cpCnt++;
                    j++;
                }

                if (cpCnt >= 3)
                {
                    //Добавляем сегмент [first,j]
                    Point[] segment = { points[i], points[j], points[k], points[m] };
                    Arrays.sort(segment);
                    LineSegment ls = new LineSegment(segment[0], segment[3]);

                    segmentsArr.add(ls);
                    segmentsNum++;
                }
                else
                {
                    //Начинаем считать заново
                    first = j;
                    curSlope = points[i].slopeTo(points[j]);
                    cpCnt = 0;
                }

            }

        }


    }

    private boolean checkPoints(Point[] points)
    {
        //TODO Возможно добавить сюда проверку на повторные точки
        if (points == null)
            return false;

        for (int i = 0; i < points.length; i++)
        {
            if (points[i] == null)
                return false;
        }

        return true;
    }

    public int numberOfSegments()        // the number of line segments
    {

    }

    public LineSegment[] segments()                // the line segments
    {

    }

    public static void main(String[] args)
    {

    }
}
