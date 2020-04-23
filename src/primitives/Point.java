package primitives;

public class Point  {
	protected Coordinate x;
	protected Coordinate y;
	
	//Constructors
	public Point() {
		super();
		x=new Coordinate();
		y=new Coordinate();
	}
	public Point(Coordinate x, Coordinate y) {
		super();
		this.x = x;
		this.y = y;
	}
	public Point(Point p) {
		x=new Coordinate(p.getX());
		y=new Coordinate(p.getY());
}

	//getter and setter
	public Coordinate getX() {
		return x;
	}
	public void setX(Coordinate x) {
		this.x = x;
	}
	public Coordinate getY() {
		return y;
	}
	public void setY(Coordinate y) {
		this.y = y;
	}
	
	
	public String toString()
	
	{
		return x.toString()+" "+y.toString();
	}
	
}
