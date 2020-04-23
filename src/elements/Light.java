package elements;

import java.awt.Color;

import primitives.PointD3;
import primitives.Vector;

public abstract class Light {
	public Color _color;
	//gets a point and only returns the color under the influence of the lighting at the same point
	public abstract Color getIntensity(PointD3 point);
	//Gets a point and returns a vector from the point to the light source.
	public abstract Vector getL(PointD3 point);

	public Light(Color _color) {
		super();
		this._color = new Color(_color.getRed(),_color.getGreen(),_color.getBlue());
	}
	public Light() {
		super();
		this._color = new Color(1,1,1);
	}
	public Color get_color() {
		return _color;
	}

	public void set_color(Color _color) {
		this._color = _color;
	}
	

}
