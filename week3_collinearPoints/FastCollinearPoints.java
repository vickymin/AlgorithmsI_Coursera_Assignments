import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

   private int N;
   private LineSegment[] segments;

   // finds all line segments containing 4 or more points
   public FastCollinearPoints(Point[] points) {
      if (points == null) {
            throw new NullPointerException();
        }
        N = points.length;
        Point[] copy = new Point[points.length];
        Point[] temp = new Point[points.length];
        N = copy.length;
        // copy from points
        for (int i = 0; i < N; i++) {
            // finds null elements
            if (points[i] == null) throw new NullPointerException();
            else {
                copy[i] = points[i];
                temp[i] = points[i];
            }
        }
        Arrays.sort(copy);
        // find duplicate elements
        for (int j = 0; j < N-1; j++) {
            if (copy[j].compareTo(copy[j+1]) == 0) throw new IllegalArgumentException();
        }
        ArrayList<LineSegment> found = new ArrayList<>();
        for (int k = 0; k < N; k++) {
            Arrays.sort(temp, copy[k].slopeOrder());
            int count = 1;
            double first = copy[k].slopeTo(temp[0]);
            ArrayList<Point> record = new ArrayList<>();
            for (int m = 0; m < N; m++) {
                double last = copy[k].slopeTo(temp[m]);
                if (first == last) {
                    record.add(temp[m]);
                    count++;
                    if (count > 2 && m == N-1) {
                        record.add(copy[k]);
                        Collections.sort(record);
                        if (record.get(0).compareTo(copy[m]) < 0) {
                            continue;
                        } else {
                            found.add(new LineSegment(record.get(0), record.get(record.size() - 1)));
                        }
                        count = 1;
                    }
                } else {
                    if (count > 2) {
                        record.add(copy[k]);
                        Collections.sort(record);
                        if (record.get(0).compareTo(copy[m]) < 0) {
                            continue;
                        } else {
                            found.add(new LineSegment(record.get(0), record.get(record.size() - 1)));
                        }
                    } else {
                        record = new ArrayList<>();
                        record.add(temp[m]);
                        count = 1;
                    }
                }
                first = last;
            }
        }
        segments = found.toArray(new LineSegment[found.size()]);
   }

   // the number of line segments
   public int numberOfSegments() {
        return segments.length;
   }

   // the line segments
   public LineSegment[] segments() {
        return Arrays.copyOf(segments, numberOfSegments());
   }

   public static void main(String[] args) {

       // read the N points from a file
       In in = new In(args[0]);
       int N = in.readInt();
       Point[] points = new Point[N];
       for (int i = 0; i < N; i++) {
           int x = in.readInt();
           int y = in.readInt();
           points[i] = new Point(x, y);
       }

       // draw the points
       StdDraw.show(0);
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
   }
}
