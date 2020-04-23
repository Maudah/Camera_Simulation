package geometries;
import java.awt.Color;
import java.util.ArrayList;

import primitives.*;
public class Triangle extends Geometry implements FlatGeometry
{
	private PointD3 p1;
	private PointD3 p2;
	private PointD3 p3;

	//Constructors
	public Triangle() {
		super();
		p1=new PointD3();
		p2=new PointD3();
		p3=new PointD3();

	}
	public Triangle(PointD3 p1, PointD3 p2, PointD3 p3,Color c) {
		super();
		this.p1 =new PointD3(p1);
		this.p2 =new PointD3(p2);
		this.p3 =new PointD3(p3);
		this.setEmmision(c);
	}

	//Getter and Setter
	public PointD3 getP1() {
		return p1;
	}
	public void setP1(PointD3 p1) {
		this.p1 = p1;
	}
	public PointD3 getP2() {
		return p2;
	}
	public void setP2(PointD3 p2) {
		this.p2 = p2;
	}
	public PointD3 getP3() {
		return p3;
	}
	public void setP3(PointD3 p3) {
		this.p3 = p3;
	}
	public Triangle(Triangle other)
	{
		super();
		this.p1 = new PointD3(other.p1);
		this.p2 = new PointD3(other.p2);
		this.p3 = new PointD3(other.p3);
	}

	@Override

	public Vector getNormal(PointD3 p) {
		Vector v1=new Vector(p1);
		v1.subtract(new Vector(p2));
		Vector v2=new Vector(p2);	
		v2.subtract(new Vector(p3));				
		Vector v3=new Vector(v1.crossProduct(v2));
		v3.normalize();
		return v3;

	}


	public boolean equals(Triangle t) {
		return this.p1.equals(t.getP1())&&
				this.p2.equals(t.getP2())&&
				this.p3.equals(t.getP3());

	}
	public String toString()
	{
		return "Triangle [p1=" + p1 + ", p2=" + p2 + ", p3=" + p3 + "]"+"color: "+this.getEmmision()+"material:  "+this.getMaterial().ToString();
	}
	@Override

	//getting ray and return array of intersection 
	public ArrayList<PointD3> FindIntersections(Ray ray)
	{
		Vector v = this.getNormal(new PointD3());
		ArrayList<PointD3> intersectionPoints = new ArrayList<PointD3>();
		Plane p = new Plane(this.p1,v,this.getEmmision());
		intersectionPoints = p.FindIntersections(ray);
		if (intersectionPoints==null||intersectionPoints.isEmpty())
		{
			return intersectionPoints; 
		}         
		Triangle tr1 = new Triangle( ray.getPoo(), p1, p2,this.getEmmision());
		Vector N1 = new Vector();
		N1 = tr1.getNormal(new PointD3());
		Triangle tr2 = new Triangle( ray.getPoo(), p2, p3,this.getEmmision());
		Vector N2 = new Vector();
		N2 = tr2.getNormal(new PointD3());
		Triangle tr3 = new Triangle (ray.getPoo(), p3, p1,this.getEmmision());
		Vector N3 = new Vector();
		N3 = tr3.getNormal(new PointD3());

		Vector v1 = new Vector(intersectionPoints.get(0));//וקטור ממרכז המצלמה לנקודת חיתוך על המשטח
		Vector v2=new Vector(ray.getPoo());//נקודה על הוקטור

		Vector sign=new Vector(v1);
		sign.subtract(v2);
		if (((sign.dotProduct(N1) > 0) && (sign.dotProduct(N2) > 0) 
				&& (sign.dotProduct(N3) > 0)) || ((sign.dotProduct(N1) < 0) && 
						(sign.dotProduct(N2) < 0) && (sign.dotProduct(N3) < 0))) 
		{
			return intersectionPoints;
		}
		intersectionPoints.clear();
		return intersectionPoints;		

	}
}


