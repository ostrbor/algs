import java.lang.IllegalArgumentException;
import java.util.Arrays;

public class FastCollinearPoints {
    private Point[] pointsArray;
    private int numberOfPointsInLine = 4;
    private int lineSegmentIndex = 0;

    public FastCollinearPoints(Point[] points) {
        for (int k = 0; k < points.length; k++) {
            if (points[k] == null) throw new IllegalArgumentException("null point is not allowed");
        }
        for (int i = 0; i < points.length - 1; i++) {
            Point p = points[i];
            for (int j = i + 1; j < points.length; j++) {
                Point q = points[j];
                if (p.compareTo(q) == 0) throw new IllegalArgumentException("same points are not allowed");
            }
        }
        pointsArray = points;
    }

    public int numberOfSegments() {
        return lineSegmentIndex + 1;
    }

    public LineSegment[] segments() {
        LineSegment[] segs = new LineSegment[pointsArray.length];

        for (int i = 0; i <= (pointsArray.length - numberOfPointsInLine); i++) {
            Point p = pointsArray[i];
            Arrays.sort(pointsArray, p.slopeOrder());
            for (int j = i + 1; j < pointsArray.length; j++) {
                Point second = pointsArray[j];
                Point third = pointsArray[j + 1];
                Point fourth = pointsArray[j + 2];
                if ((p.slopeTo(second) == p.slopeTo(third)) && (p.slopeTo(second) == p.slopeTo(fourth))) {
                    LineSegment ls = new LineSegment(p, fourth);
                    segs[lineSegmentIndex++] = ls;
                }
            }
        }

        LineSegment[] res = new LineSegment[lineSegmentIndex + 1];
        for (int i = 0; i < res.length; i++) res[i] = segs[i];
        return res;
    }
}
