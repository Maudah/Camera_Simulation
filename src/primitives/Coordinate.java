package primitives;

public class Coordinate 
{
	private double x;
	
	//Constructors
	public Coordinate() {
		super();
		
	}
	public  Coordinate(Coordinate c) {
	this.setX(c.getX());
	
}
	public Coordinate(double t) {
		super();
		this.x=t;
		// TODO Auto-generated constructor stub
	}
	
	//getter and setter
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}	
	
	public void Add(Coordinate y)
	{
		this.x+=y.getX();
	}
	public double Substrct(Coordinate y)
	{
		return this.x-=y.x;
	}
	public boolean equals(Coordinate c1)
	{
		if(c1.getX()==x)
			return true;
		return false;
		
	}
	public String toString()
	{
		return Double.toString(x);
	}

}

