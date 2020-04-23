package primitives;

public class Material {
	private double _Kd; // Diffusion attenuation coefficient –
	private double _Ks; // Specular attenuation coefficient – 
	private double _n;  // Transparency index
	private double _kr;//Reflection:=no reflection ,1=most reflection
	private double _kt;//Transparency:0=no transparency,1=most transparency

	
	//Constructors
	public Material(double _Kd, double _Ks, double _n,double _Kr, double _Kt) {
		super();
		this._Kd = _Kd;
		this._Ks = _Ks;
		this._n = _n;
		this._kr = _Kr;
		this._kt = _Kt;
	}
	public Material(double _Kd, double _Ks, double _n) 
	{
		super();
		this._Kd = _Kd;
		this._Ks = _Ks;
		this._n = _n;
		this._kr=0;
		this._kt=0;
	}
	public Material()
	{
		super();
		// TODO Auto-generated constructor stub
		this._Kd = 1;
		this._Ks = 1;
		this._n = 1;
		this._kr=0;
		this._kt=0;
	}
	public Material(Material m) {

		super();
		this._Kd = m.get_Kd();
		this._Ks =m.get_Ks();
		this._n =m.get_n();
		this._kr = m.get_kr();
		this._kt =m.get_kt();
	}
	
	//getter and setter
	public double get_kr() {
		return _kr;
	}
	public void set_kr(double _kr) {
		this._kr = _kr;
	}
	public double get_kt() {
		return _kt;
	}
	public void set_kt(double _kt) {
		this._kt = _kt;
	}
	public double get_Kd() {
		return _Kd;
	}
	public void set_Kd(double _Kd) {
		this._Kd = _Kd;
	}
	public double get_Ks() {
		return _Ks;
	}
	public void set_Ks(double _Ks) {
		this._Ks = _Ks;
	}
	public double get_n() {
		return _n;
	}
	public void set_n(double _n) {
		this._n = _n;
	}
	
	public String ToString()
	{
		return "ks= "+_Ks+"kd= "+_Kd+"n= "+_n+ "kr= "+_kr+"kt= "+_kt;
	}
	public  boolean equals(Object v)
	{
		Material m=(Material)v;
		if(m.get_Kd()==_Kd&&m.get_Ks()==_Ks&&m.get_n()==_n)
			return true;
		return false;
	}
	
}
