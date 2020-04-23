package elements;

import java.awt.Color;

import primitives.PointD3;
import primitives.Vector;

public class PointLight extends Light {
	PointD3 _position;
	double _kc;
	double _kl;
	double _kq;
	public PointLight(Color _color, PointD3 _position, double _kc, double _kl, double _kq) {
		this.set_color(_color);
		this._position =new PointD3( _position);
		this._kc = _kc;
		this._kl = _kl;
		this._kq = _kq;
	}
	public PointD3 get_position() {
		return _position;
	}
	public void set_position(PointD3 _position) {
		this._position = new PointD3(_position);
	}
	public double get_kc() {
		return _kc;
	}
	public void set_kc(double _kc) {
		this._kc = _kc;
	}
	public double get_kl() {
		return _kl;
	}
	public void set_kl(double _kl) {
		this._kl = _kl;
	}
	public double get_kq() {
		return _kq;
	}
	public void set_kq(double _kq) {
		this._kq = _kq;
	}
	public PointLight() {
		super(new Color(255, 255, 255));
		this._position =new PointD3();
		this._kc = 0;
		this._kl = 0;
		this._kq = 0;
	}
	//gets a point and only returns the color under the influence of the lighting at the same point
	public  Color getIntensity(PointD3 point)
	{	
		double d=this._position.Distance(point);
		double temp1=(_kc+_kl*d+_kq*Math.pow(d, 2));		
		double tc1=(get_color().getRed()/temp1);
		double tc2=get_color().getGreen()/temp1;
		double tc3=get_color().getBlue()/temp1;
		int c1=(int)tc1;
		int c2=(int)tc2;
		int c3=(int)tc3;		
	    return new Color(Math.min(255, c1),Math.min(255, c2),Math.min(255, c3));
	}
	//Gets a point and returns a vector from the point to the light source.
	public  Vector getL(PointD3 point)
	{		
		PointD3 temppoint=new PointD3(point);
		temppoint.subtract(new Vector(_position));
		return new Vector(temppoint);
	}
	public  boolean equals(Object v)
	{
		
		PointLight p1=(PointLight)v;
		if(p1._kc==_kc&&p1._kl==_kl&&p1._kq==_kq&&p1._position.equals(_position)==true&&p1._color==_color)
			return true;
		return false;
	}
	public String toString()
	{
		return "point : "+get_position().toString()+" color: "+_color+" kc: "+_kc+" kl: "+_kl+" kq: "+_kq;
	}

}
