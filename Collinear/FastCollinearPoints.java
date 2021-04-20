/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;

public class FastCollinearPoints
{
    public FastCollinearPoints(
            Point[] points)     // finds all line segments containing 4 or more points
    {
        if (!checkPoints(points))
            throw new IllegalArgumentException();

        for (int i = 0; i < points.length; i++)
        {
            Arrays.sort(points, j, points.length, points[i].slopeOrder()/*Comparator for point i*/);
            for (int j = i + 1; j < points.length; j++)
            {
                //Проверяем, что три или более точек имеют одинаковый наклон с i-й точкой

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
