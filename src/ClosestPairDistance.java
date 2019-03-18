
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author stuart spiegel
 * Given a set of points in a plane, find the squared distance of the closest pair of points.
 * The first line will be an integer n, the total number of points.
 * The next n lines will have the x,y coordinates of the points.
 *
 */
public class ClosestPairDistance extends Point  {


	//fields
	private static Point best1;
	private static Point best2;
	private static double bestDistance;


	public static void main(String[] args) {
		//read input
		Scanner input = new Scanner(System.in);

		int NUM_POINTS = input.nextInt();

		Point[] points = new Point[NUM_POINTS];
		for (int i = 0; i < NUM_POINTS; i++) {
			int x = input.nextInt();
			int y = input.nextInt();

			points[i] = new Point(x, y);
		}
		ClosestPairDistance closest = new ClosestPairDistance(points);
		System.out.print(closest.either());
		System.out.print(closest.other());
		
	}
	//return point 1
	public Point either() {

		return best1;
	}
	//return the other point
	public Point other() {

		return best2;
	}
	//return the distance between the two closest points
	public double distance() {

		return bestDistance;
	}

	/**
	 * 
	 * if closest pair is true do the operation to find the squared distance and return
	 */
	public ClosestPairDistance(Point [] points) {
		super(x, y);

		int n = points.length;

		if (n <= 1) return;

		Point[] pointsByX = new Point[n];

		//sort the points by x-coordinate
		for (int i = 0; i < n; i++) {

			pointsByX[i] = points[i];
			//            Arrays.sort(pointsByX);
			System.out.println(pointsByX[i]);
		}

		Arrays.sort(pointsByX);


		//check points for same ones 
		for (int i = 0; i < n-1; i++) {
			if (pointsByX[i].equals(pointsByX[i+1])) {
				bestDistance = 0;
				best1 = pointsByX[i];
				best2 = pointsByX[i+1];
				return;
			}
		}

		// sort by y-coordinate (but not yet sorted) 
		Point[] pointsByY = new Point[n];

		for (int i = 0; i < n; i++)
			pointsByY[i] = pointsByX[i];

		// auxiliary array
		Point[] aux = new Point[n];

		closest(pointsByX, pointsByY, aux, 0, n-1);

	}
	private static double closest(Point[] pointsByX, Point[] pointsByY, Point[] aux, int low, int high) {
		if (high <= low) return Double.POSITIVE_INFINITY;

		int mid = low + (high - low) / 2;

		Point median = pointsByX[mid];

		// compute closest pair with both endpoints in left sub array or both in right sub array
		double delta1 = closest(pointsByX, pointsByY, aux, low, mid);
		double delta2 = closest(pointsByX, pointsByY, aux, mid+1, high);
		double delta = Math.min(delta1, delta2);

		// merge back so that pointsByY are sorted by y-coordinate
		merge(pointsByY, aux, low, mid, high);

		// aux[0..m-1] = sequence of points closer than delta, sorted by y-coordinate
		int m = 0;

		for (int i = low; i <= high; i++) {
			if (Math.abs(pointsByY[i].getX(best1) - median.getX(best1)) < delta)
				aux[m++] = pointsByY[i];
		}

		// compare each point to its neighbors with y-coordinate closer than delta
		for (int i = 0; i < m; i++) {
			for (int j = i+1; (j < m) && (aux[j].getY(best2) - aux[i].getY(best2) < delta); j++) {

				double distance = (int) aux[i].distance(aux[j]);
				if (distance < delta) {
					delta = distance;
					if (distance < bestDistance) {
						bestDistance = delta;
						best1 = aux[i];
						best2 = aux[j];

					}
				}
			}
		}
		return delta;
	}

	/**
	 * 
	 * Perform a stable merge of Point (a) and Point (b) using the auxiliary array 
	 * as temporary storage. This method assumes that the sub-arrays are already sorted
	 */
	public static void merge(Point[] pointsByY, Point[] aux, int low, int mid, int high) {

		//copy 
		for (int k = low; k <= high; k++) {
			aux[k] = pointsByY[k];
		}

		// merge back to Comparable[] a
		int i = low;
		int j = mid+1;

		for (int k = low; k <= high; k++) {
			if      (i > mid)              pointsByY[k] = aux[j++];
			else if (j > high)             pointsByY[k] = aux[i++];
			else if (compareTo(aux[j], aux[i])) pointsByY[k] = aux[j++];
			else                           pointsByY[k] = aux[i++];
		}
	}


}


