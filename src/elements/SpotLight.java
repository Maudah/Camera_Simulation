package elements;

import java.awt.Color;

import primitives.PointD3;
import primitives.Vector;

public class SpotLight extends PointLight {
	Vector _direction;

	public SpotLight(Color _color, PointD3 _position, double _kc, double _kl, double _kq, Vector _direction) {
		super(_color, _position, _kc, _kl, _kq);
		this._direction =new Vector( _direction);
	}

	public SpotLight() {
		super();
		_direction=new Vector();
		
	}

	public SpotLight(Color _color, PointD3 _position, double _kc, double _kl, double _kq) {
		super(_color, _position, _kc, _kl, _kq);
		
	}
	@Override
	//gets a point and only returns the color under the influence of the lighting at the same point
	public  Color getIntensity(PointD3 point)
	{
		_direction.normalize();
		Vector v=new Vector(getL(point));
		v.normalize();
		double d=this._position.Distance(point);
		double temp1=1/(_kc+_kl*d+_kq*Math.pow(d, 2));
		temp1=temp1*v.dotProduct(_direction);
		if(temp1>1)
			temp1=1;
		temp1=Math.abs(temp1);
		double tc1=((get_color().getRed())*temp1);
		double tc2=(get_color().getGreen())*temp1;
		double tc3=(get_color().getBlue())*temp1;	
		int c1=(int)Math.min(255, tc1);
		int c2=(int)Math.min(255, tc2);
		int c3=(int)Math.min(255, tc3);
	    return new Color(c1, c2, c3);
	}	
	//Gets a point and returns a vector from the point to the light source.
	public  Vector getL(PointD3 point)	
	{
		Vector vec= new Vector(point);
		vec.subtract(new Vector(_position));
		return vec;
	}
	public  boolean equals(Object v)
	{

		SpotLight p1=(SpotLight)v;
		if(p1._kc==_kc&&p1._kl==_kl&&p1._kq==_kq&&p1._position.equals(_position)==true&&p1._color==_color&&p1._direction.equals(_direction)==true)
			return true;
		return false;
	}
	public String toString()
	{
		return "point : "+get_position().toString()+" diretion: "+_direction.toString()+" color: "+_color+" kc: "+_kc+" kl: "+_kl+" kq: "+_kq;
	}
}
