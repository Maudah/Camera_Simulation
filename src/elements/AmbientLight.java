package elements;

import java.awt.Color;


import primitives.PointD3;
import primitives.Vector;

public class AmbientLight extends Light  {
	double _ka=0.1;

	//Constructors
	public AmbientLight(double _ka,Color c) {
		super(c);
		this._ka = _ka;		
	}
	public AmbientLight(AmbientLight abLight) {
		super(abLight._color);
		this._ka = abLight._ka;		
	}
	public AmbientLight() {
		super();
		this._ka = 0.1;		
	}
	
	//getter and setter
	public double get_ka() {
		return _ka;
	}

	public void set_ka(double _ka) {
		this._ka = _ka;
	}


	//gets ray and returns array of intersection 
	public  Color getIntensity(PointD3 point)
	{
		double r=this.get_color().getRed()*_ka;
		double g=this.get_color().getGreen()*_ka;
		double b=this.get_color().getBlue()*_ka;
		return new Color((int)Math.min(255, r), (int)Math.min(255, g), (int)Math.min(255, b));

	}
	// gets point D3 and returns vector that goes through that point
	public Vector getL(PointD3 point)
	{		
		Vector v1 =new Vector(point);
		return v1;
	}
	public  boolean equals(Object v)
	{
		AmbientLight ambientLight=(AmbientLight)v;
		if(this._color==ambientLight._color&&_ka==ambientLight._ka)
			return true;
		return false;
	}
	public String toString()
	{
		return "ka: "+_ka+" color: "+_color;
	}

}
