package geometries;

import java.awt.Color;
import java.util.ArrayList;

import primitives.Material;
import primitives.PointD3;
import primitives.Ray;
import primitives.Vector;

public class Plane extends Geometry implements FlatGeometry
{
	PointD3 p;
	Vector vector;

	//Constructors
	public Plane(PointD3 p, Vector vector,Color emColor) {	
		this.setEmmision(emColor);
		this.p =new PointD3(p) ;
		this.vector =new Vector(vector.getHead()) ;

	}
	public Plane() {
		p=new PointD3();
		vector=new Vector();
		this.setEmmision(new Color(0, 0, 0));
		this.setMaterial(new Material());
		// TODO Auto-generated constructor stub
	}
	public Plane(Plane plane)
	{
		p=new PointD3(plane.getP());
		vector=new Vector(plane.getVector().getHead());
		this.setEmmision(plane.getEmmision());
		this.setMaterial(plane.getMaterial());
	}

	//getter and setter
	public PointD3 getP() {
		return p;
	}

	public void setP(PointD3 p) {
		this.p = p;
	}

	public Vector getVector() {
		return vector;
	}

	public void setVector(Vector vector) {
		this.vector = vector;

	}
	
	
	public boolean equals(Plane p1)
	{
		return p1.getP().equals(p)&&p1.getVector().equals(p1.getVector());
	}
	@Override

	public Vector getNormal(PointD3 p) {
		// TODO Auto-generated method stub
		return vector;
	}
	public String toString()
	{
		return "plane [p=" + p.toString() + ", vector=" +vector.toString();
	}
	@Override
	public ArrayList<PointD3> FindIntersections(Ray ray)
	{
		ArrayList<PointD3> intersectionPoints = new ArrayList<PointD3>();
		Vector v = new Vector(ray.getPoo());// P0
		Vector direction = new Vector(ray.getDirection());//Direction vector
		v.subtract(new Vector(p));
		double mechane = vector.dotProduct(direction);//V*N
		if (mechane != 0) 
		{
			double t = -(vector.dotProduct(v) / mechane);//-n*(p0-q0)\V*N
			if (t > 0) 
			{
				direction.scale(t);
				PointD3 p = new PointD3(ray.getPoo());
				p.add(direction);
				intersectionPoints.add(p);
			}
		}
		if(intersectionPoints.size()==0)
			return null;
		return intersectionPoints;		




	}


}
