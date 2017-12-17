import java.lang.IllegalArgumentException;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private Point[] pointsArray;
    private int numberOfPointsInLine = 4;
    private int lineSegmentIndex = 0;

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
        pointsArray = points;
    }

    public int numberOfSegments() {
        return lineSegmentIndex + 1;
    }

    private boolean isOnOneLine(Point[] p) {
        double slope = p[0].slopeTo(p[1]);
        for (int i = 1; i < 3; i++) {
            double nextSlope = p[i].slopeTo(p[i + 1]);
            if (slope != nextSlope) return false;
            else slope = nextSlope;
        }
        return true;
    }

    private LineSegment calcLineSegment(Point[] points) {
        Point min = points[0];
        for (int i = 1; i <= 3; i++) {
            if (points[i].compareTo(min) < 0) min = points[i];
        }
        Point max = points[0];
        for (int i = 1; i <= 3; i++) {
            if (points[i].compareTo(max) > 0) max = points[i];
        }
        return new LineSegment(min, max);
    }

    public LineSegment[] segments() {
        LineSegment[] segs = new LineSegment[pointsArray.length];
        for (int i = 0; i <= (pointsArray.length - numberOfPointsInLine); i++) {
            for (int j = i + 1; j <= (pointsArray.length - numberOfPointsInLine + 1); j++) {
                for (int k = i + 2; k <= (pointsArray.length - numberOfPointsInLine + 2); k++) {
                    for (int l = i + 3; l <= (pointsArray.length - numberOfPointsInLine + 3); l++) {
                        Point[] p = {pointsArray[i], pointsArray[j], pointsArray[k], pointsArray[l]};
                        if (isOnOneLine(p)) {
                            LineSegment ls = calcLineSegment(p);
                            segs[lineSegmentIndex++] = ls;
                        }
                    }
                }
            }
        }

        LineSegment[] res = new LineSegment[lineSegmentIndex + 1];
        for (int i = 0; i < res.length; i++) res[i] = segs[i];
        return res;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}