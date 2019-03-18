
public class Point {
	protected static int x;
	protected static int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		
	}
	
	public static boolean compareTo(Point first, Point second) {
		if(first.getX(first) < first.getX(second) && first.getY(first) < first.getY(second)) {
			return true;
		}
		else if(first.getX(first) == first.getX(second) && first.getY(first) == first.getY(second)) {
			return false;
		}
		else {
			return false;
		}
		
	}
	public int getX(Point x) {
		return x.x;
		
	}
	public int getY(Point y) {
		return y.y;
		
	}
	public void setX(Point x, int newX) {
		x.x = newX;
		
	}
	public void setY(Point y, int newY) {
		y.y = newY;
		
	}



	public int distance(Point point) {
		
		return point.distance(point);
	}
	
	
}
