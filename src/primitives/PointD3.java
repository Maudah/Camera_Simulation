package primitives;

public class PointD3 extends Point {
	private	Coordinate z;
	
	//Constructors
	public PointD3() {
		super();
		z=new Coordinate();
		
	}
	public PointD3(Coordinate x, Coordinate y,Coordinate z) {
		super(x, y);
		this.z = z;
		// TODO Auto-generated constructor stub
	}
	public PointD3(double x, double y,double z) {
		this.x=new Coordinate(x);
		this.y=new Coordinate(y);
		this.z=new Coordinate(z);


	}
	public PointD3(PointD3 p)
	{
		x=new Coordinate(p.getX());
		y=new Coordinate(p.getY());
		z=new Coordinate(p.getZ());
	}
	
	//getter and setter
	public Coordinate getZ() {
		return z;
	}
	public void setZ(Coordinate z) {
		this.z = z;
	}

	public void add(Vector v)
	{
		this.getX().Add(v.getHead().getX());
		this.getY().Add(v.getHead().getY());
		this.getZ().Add(v.getHead().getZ());
		
	}
	public void subtract(Vector v)
	{
		this.getX().Substrct(v.getHead().getX());
		this.getY().Substrct(v.getHead().getY());
		this.getZ().Substrct(v.getHead().getZ());
		
	}
	public double Distance(PointD3 p1)
	{
		double powX=Math.pow((this.getX().getX()-p1.getX().getX()), 2);	
		double powY=Math.pow((this.getY().getX()-p1.getY().getX()),2);
		double powZ=Math.pow((this.getZ().getX()-p1.getZ().getX()),2);
		return Math.sqrt(powX+powY+powZ);
	}
	@Override
	public boolean equals(Object p)
	{
		PointD3 p1=(PointD3)p;
		if(p1.getZ().equals(z)==true&&p1.getX().equals(x)==true&&p1.getY().equals(y)==true)
			return true;
		return false;	
		//return true;
	}
	
	public String toString()
	{
		return getX().toString()+" , "+getY().toString()+" , "+getZ().toString();
	}

	
}
