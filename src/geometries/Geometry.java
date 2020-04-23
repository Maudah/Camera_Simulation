package geometries;

import java.awt.Color;
import java.util.ArrayList;

import primitives.*;


public abstract class Geometry
{
	private Color emmision;
	private Material material;

	//Constructors
	public Geometry(Color colors, Material material) {
		super();
		this.emmision =new Color(colors.getRed(),colors.getGreen(),colors.getBlue());
		this.material =new Material(material);
	}
	public Geometry()
	{}
	public Geometry(Geometry g) {
		super();
		this.emmision =new Color(g.emmision.getRed(),g.emmision.getGreen(),g.emmision.getBlue());
		this.material =new Material(g.material);
	}
	
	//getter and setter
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material =new Material( material);
	}
	public Color getEmmision() {
		return emmision;
	}
	public void setEmmision(Color colors) {
		this.emmision = new Color(colors.getRed(),colors.getGreen(),colors.getBlue());
	}
	
	public abstract ArrayList<PointD3> FindIntersections(Ray myRay);
	
	public abstract Vector getNormal(PointD3 p);


}
