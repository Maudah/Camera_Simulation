package geometries;

import java.awt.Color;
import java.util.ArrayList;

import primitives.*;

public class Cylinder extends RadialGeometry 
{

	PointD3 _axisPoint ;
	Vector _axisDirection;

	//Constructors
	public Cylinder(Color color, double radius, PointD3 myPoint, Vector myDirection) {
		this.radius=radius;
		this.setEmmision(color);
		this._axisPoint = myPoint;
		this._axisDirection = myDirection;
	}
	public Cylinder (Cylinder c) 
	{
		this.setEmmision(c.getEmmision());
		this.setRadius(c.getRadius());
		this._axisDirection=c._axisDirection;
		this._axisPoint=c._axisPoint;
	}
	public Cylinder () {

		super();
		Coordinate a=new Coordinate(0);
		this._axisPoint = new PointD3(a,a,a);
		PointD3 p=new PointD3 (a,a,a);
		this._axisDirection=new Vector(p);
	}

	// Getters and Setters
	public PointD3 getMYPoint() {
		return _axisPoint;
	}

	public void setMYPoint(PointD3 myPoint
			) {
		this._axisPoint = myPoint;
	}

	public Vector getMYDirection() {
		return _axisDirection;
	}

	public void setMYDirection(Vector myDirection) {
		this._axisDirection = myDirection;
	}


	
	@Override
	public String toString() {
		return "Cylinder [myPoint=" + _axisPoint + ", myDirection=" + _axisDirection + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cylinder other = (Cylinder) obj;
		if (_axisDirection == null) {
			if (other._axisDirection != null)
				return false;
		} else if (!_axisDirection.equals(other._axisDirection))
			return false;
		if (_axisPoint == null) {
			if (other._axisPoint != null)
				return false;
		} else if (!_axisPoint.equals(other._axisPoint))
			return false;
		return true;
	}


	@Override
	public Vector getNormal(PointD3 p)
	{
		p.subtract(new Vector(_axisPoint));
		Vector v =new Vector(p);
		v.normalize();
		return v;
	}

	//getting ray and return array of intersection 
	@Override

	public ArrayList<PointD3> FindIntersections (Ray ray)
	{
		return null;
	}
}
