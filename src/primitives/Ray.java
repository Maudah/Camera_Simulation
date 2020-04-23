package primitives;

public class Ray {
	private PointD3 poo;
	private Vector direction;

	//Constructors
	public Ray() {
		super();
		poo = new PointD3();
		direction = new Vector();

	}

	public Ray(PointD3 poo, Vector direction) {
		super();
		this.poo = new PointD3(poo);
		this.direction = new Vector(direction);
	}

	public Ray(Ray ray) {
		this.poo = new PointD3(ray.getPoo());
		this.direction = new Vector(ray.getDirection());
	}

	//getter and setter
	public PointD3 getPoo() {
		return poo;
	}

	public void setPoo(PointD3 poo) {
		this.poo = poo;
	}

	public Vector getDirection() {
		return direction;
	}

	public void setDirection(Vector direction) {
		this.direction = direction;
	}

	public boolean equals(Ray r) {
		if (this.getPoo().equals(r.getPoo()) && this.getDirection().equals(r.getDirection()))
			return true;
		return false;
	}

	public String toString() {
		return this.getPoo().toString() + " " + this.getDirection().toString();
	}
}
