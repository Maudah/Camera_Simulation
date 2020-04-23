package geometries;

import java.awt.Color;
import java.util.ArrayList;

//import javax.swing.text.Position;

import primitives.*;

public class Sphere extends RadialGeometry {
	PointD3  center;


	//Constructors
	public Sphere(PointD3 p,double radius,Color c) {
		this.radius=radius;
		this.center =new PointD3(p);
		this.setEmmision(c);
	}
	public Sphere() {
		super();
		this.center=new PointD3();

	}
	public Sphere(Sphere c) {
		center=new PointD3(c.getP());
		radius=c.getRadius();
	}

	//getter and setter
	public PointD3 getP() {
		return center;
	}
	public void setP(PointD3 p) {
		this.center = p;
	}

	public boolean equals(Sphere c)
	{
		boolean flag=(c.radius==radius);
		return flag&&c.center.equals(center);
	}

	public String toString() 
	{
		return "center=" + center + ", radius=" + this.getRadius() +"color: "+this.getEmmision()+"material:  "+this.getMaterial().ToString();
	}
	public Vector getNormal(PointD3 p1) 
	{
		Vector v1 =new Vector(p1);
		v1.subtract(new Vector(center));
		v1.normalize();
		return v1;

	}
	@Override

	//getting ray and return array of intersection 
	public ArrayList<PointD3> FindIntersections (Ray ray)
	{	
		Vector L1 = new Vector(center);
		Vector L2=new Vector(ray.getPoo());
		L1.subtract(L2);//o-p0
		double tm = L1.dotProduct(ray.getDirection());//tm=l*v
		double d = Math.sqrt(Math.pow(L1.lenght(), 2) - Math.pow(tm, 2));//(|L|^2+tm^2)^0.5 
		ArrayList<PointD3> intersectionPoints = new ArrayList<PointD3>();
		if (d > this.getRadius()) 
		{
			return intersectionPoints;
		}
		double th = Math.sqrt(Math.pow(this.getRadius(), 2) - Math.pow(d, 2));//(r^2-t^2)^0.5
		double t1 = tm - th;
		double t2 = tm + th;
		if (t1 > 0) 
		{
			Vector direct = new Vector(ray.getDirection());
			direct.normalize();
			direct.scale(t1);
			PointD3 P1 = new PointD3(ray.getPoo());
			P1.add(new Vector(direct));
			intersectionPoints.add(P1);
		}

		if (t2 > 0) 
		{
			Vector dirc = new Vector(ray.getDirection());
			dirc.normalize();
			dirc.scale(t2);
			PointD3 P2 = new PointD3(ray.getPoo());
			P2.add(new Vector(dirc));
			intersectionPoints.add(P2);
		}
		if (intersectionPoints.size()==0)
			return null;
		return intersectionPoints;
	}
}
