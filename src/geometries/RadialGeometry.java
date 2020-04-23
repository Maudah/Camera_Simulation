package geometries;

import java.awt.Color;
import java.util.ArrayList;

import primitives.PointD3;
import primitives.Ray;
import primitives.Vector;

public class RadialGeometry extends Geometry{
	double radius;

	//Constructors
	public RadialGeometry() {

		super();
		// TODO Auto-generated constructor stub
	}
	public RadialGeometry(double radius) {
		super();
		this.radius = radius;
	}
	public RadialGeometry(Color color, double radius) {
		super();
		this.radius = radius;
	}

	//getter and setter
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}


	@Override
	public Vector getNormal(PointD3 p) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	//getting ray and return array of intersection 
	public ArrayList<PointD3> FindIntersections(Ray myRay)
	{
		return null;
	}
}
