package elements;

import java.awt.Color;

//import primitives.Coordinate;
import primitives.PointD3;
import primitives.Vector;

public class DirectionalLight extends Light {
	Vector _direction;
	
	//Constructors
	public DirectionalLight() {
		super();
		this._direction = new Vector();
	}
	public DirectionalLight(Color _color, Vector _direction) {
		super(_color);
		this._direction = new Vector(_direction);
	}

	//getter and setter
	public Vector get_direction() {
		return _direction;
	}

	public void set_direction(Vector _direction) {
		this._direction = _direction;
	}
	//gets a point and only returns the color under the influence of the lighting at the same point
	public  Color getIntensity(PointD3 point)
	{
		return this.get_color();
	}
	//Gets a point and returns a vector from the point to the light source.
	public  Vector getL(PointD3 point)
	{
		Vector vec= new Vector(point);
		vec.subtract(_direction);
		return vec;
	}
	public  boolean equals(Object v)
	{
		DirectionalLight d=(DirectionalLight)v;
		if(d._color==this._color&&d._direction.equals(_direction)==true)
			return true;
		return false;
	}
	public String toString()
	{
		return "direction: "+_direction.toString()+" color: "+_color;
	}

}
