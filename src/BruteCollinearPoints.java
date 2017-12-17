import java.lang.IllegalArgumentException;

public class BruteCollinearPoints {
    private Point[] points;

    public BruteCollinearPoints(Point[] points) {
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
        points = points;
    }

    public int numberOfSegments() {

    }

    public LineSegment[] segments() {

    }
}