import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

   private int N;
   private LineSegment[] segments;

   // finds all line segments containing 4 points
   public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }
        N = points.length;
        Point[] copy = new Point[points.length];
        N = copy.length;
        // copy from points
        for (int i = 0; i < N; i++) {
            // finds null elements
            if (points[i] == null) throw new NullPointerException();
            else copy[i] = points[i];
        }
        Arrays.sort(copy);
        // find duplicate elements
        for (int j = 0; j < N-1; j++) {
            if (copy[j].compareTo(copy[j+1]) == 0) throw new IllegalArgumentException();
        }
        ArrayList<LineSegment> found = new ArrayList<>();
        for (int p = 0; p < N-3; p++) {
            for (int q = p+1; q < N-2; q++) {
                for (int r = q+1; r < N-1; r++) {
                    if (copy[p].slopeTo(copy[q]) == copy[q].slopeTo(copy[r])) {
                        for (int s = r+1; s < N; s++) {
                            if (copy[q].slopeTo(copy[r]) == copy[r].slopeTo(copy[s])) {
                                found.add(new LineSegment(copy[p], copy[s]));
                            }
                        }
                    }
                }
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
       BruteCollinearPoints collinear = new BruteCollinearPoints(points);
       for (LineSegment segment : collinear.segments()) {
           StdOut.println(segment);
           segment.draw();
       }
   }
}
