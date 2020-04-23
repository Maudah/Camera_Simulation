package elements;
import java.util.ArrayList;

import primitives.*;

public class camera {
	PointD3 p3d;
	Vector VUP;
	Vector VRIGHT;
	Vector VTOWARD;

	public PointD3 getP3d() {
		return p3d;
	}

	public void setP3d(PointD3 p3d) {
		this.p3d = p3d;
	}

	public Vector getVUP() {
		return VUP;
	}

	public void setVUP(Vector vUP) {
		VUP = vUP;
	}

	public Vector getVRIGHT() {
		return VRIGHT;
	}

	public void setVRIGHT(Vector vRIGHT) {
		VRIGHT = vRIGHT;
	}

	public Vector getVTOWARD() {
		return VTOWARD;
	}

	public void setVTOWARD(Vector vTOWARD) {
		VTOWARD = vTOWARD;
	}

	public camera(PointD3 p3d, Vector vUP, Vector vRIGHT) {
		super();
		this.p3d =new PointD3( p3d);
		VUP =new Vector(new PointD3(new Coordinate(1), new Coordinate(0), new Coordinate(0)));
		VTOWARD =new Vector( new PointD3(new Coordinate(0), new Coordinate(0), new Coordinate(-1)));
		VRIGHT =new Vector(new PointD3(new Coordinate(0), new Coordinate(-1), new Coordinate(0)));
	}
	public camera(PointD3 p3d, Vector vUP, Vector vRIGHT, Vector vTOWARD) {
		super();
		this.p3d =new PointD3( p3d);
		VUP =new Vector(new PointD3(new Coordinate(0), new Coordinate(-1), new Coordinate(0)));
		VTOWARD =new Vector( new PointD3(new Coordinate(0), new Coordinate(0), new Coordinate(-1)));
		VRIGHT =new Vector(new PointD3(new Coordinate(1), new Coordinate(0), new Coordinate(0)));
	}

	public camera() {
		super();
		this.p3d =new PointD3(0,0,0);
		VUP =new Vector(new PointD3(new Coordinate(0), new Coordinate(-1), new Coordinate(0)));
		VTOWARD =new Vector( new PointD3(new Coordinate(0), new Coordinate(0), new Coordinate(-1)));
		VRIGHT =new Vector(new PointD3(new Coordinate(1), new Coordinate(0), new Coordinate(0)));
}
	public camera(camera caa)
	{
		this.p3d=new PointD3(caa.getP3d());
		this.VRIGHT=new Vector(caa.getVRIGHT());
		this.VTOWARD=new Vector(caa.getVTOWARD());
		this.VUP=new Vector(caa.getVUP());
	}
	public boolean equals(camera c)
	{
		if(p3d.equals(c.getP3d())==true&&VUP.getHead().equals(c.VUP.getHead())==true&&VRIGHT.getHead().equals(c.VRIGHT.getHead())==true&&VTOWARD.getHead().equals(c.VTOWARD.getHead())==true)
			return true;
		return false;
	}

	public String toString()
	
	{
		return "point: "+p3d.toString()+" v up: "+VUP.toString()+" v right: "+VRIGHT.toString()+" v toward: "+VTOWARD.toString();
	}
	//Builds ray which goes through pixel
	public Ray ConstructRayThroughPixel (int Nx, int Ny, double x, double y, double screenDist, double screenWidth,double screenHeight)
	{
		Vector vRIGHT=new Vector(VRIGHT);//y
		Vector vUP=new Vector(VUP);//x
		Vector pc=new Vector(new PointD3(new Coordinate(0), new Coordinate(0), new Coordinate(-screenDist)));
		double rx=screenWidth/Nx;//pixel width		
		double ry=screenHeight/Ny;//pixel height
		double nx=Nx;//number of pixels along the screen
		double ny=Ny;//number of pixels across the screen
		double midX=(x-(nx/2))*rx+rx/2;//The middle of the pixel
		double midY=(y-(ny/2))*ry+ry/2;//The middle of the pixel
		vRIGHT.scale(midX);//X
		vUP.scale(midY);//Y
		vRIGHT.subtract(vUP);
		pc.add(vRIGHT);
		Vector myVec=new Vector(pc);
		myVec.subtract(new Vector(p3d));
		myVec.normalize();
		Ray  myray=new Ray(pc.getHead(), myVec);	
		return myray;
		
	}
	
	//Builds ray which goes through pixel
	public ArrayList<Ray>  ConstructRayThroughPixel2 (int Nx, int Ny, double x, double y, double screenDist, double screenWidth,double screenHeight)
	{
		Vector vRIGHT=new Vector(VRIGHT);//y
		Vector vUP=new Vector(VUP);//x
		Vector pc=new Vector(new PointD3(new Coordinate(0), new Coordinate(0), new Coordinate(-screenDist)));
		double rx=screenWidth/Nx;//pixel width		
		double ry=screenHeight/Ny;//pixel height
		double nx=Nx;//number of pixels along the screen
		double ny=Ny;//number of pixels across the screen
		double midX=(x-(nx/2))*rx+rx/2;//The middle of the pixel
		double midY=(y-(ny/2))*ry+ry/2;//The middle of the pixel
		vRIGHT.scale(midX);//X
		vUP.scale(midY);//Y
		vRIGHT.subtract(vUP);
		pc.add(vRIGHT);
		Vector myVec=new Vector(pc);
		myVec.subtract(new Vector(p3d));
		myVec.normalize();
		Ray  myray=new Ray(pc.getHead(), myVec);	
		ArrayList<Ray> temp=new ArrayList<Ray>();
		temp.add(myray);
		return temp;
		
	}
	
	//improvement
	//Builds ray which goes through pixel
	public ArrayList<Ray> ConstructRayThroughPixel1 (int Nx, int Ny, double x, double y, double screenDist, double screenWidth,double screenHeight)
	{
		
		double rx=screenWidth/Nx;//pixel's width		
		double ry=screenHeight/Ny;//pixel's height
		double nx=Nx;getClass();//number of pixels along the screen
		double ny=Ny;//number of pixels across the screen
    	 ArrayList<Ray> theArry=new ArrayList<Ray>();
    	 
		Vector vRIGHT=new Vector(VRIGHT);
		Vector vUP=new Vector(VUP);
		Vector pc=new Vector(new PointD3(new Coordinate(0), new Coordinate(0), new Coordinate(-screenDist)));
		double midX=(x-(nx/2))*rx+rx/2;//the middle of the pixel
		double midY=(y-(ny/2))*ry+ry/2;//the middle of the pixel	
		vRIGHT.scale(midX);//X
		vUP.scale(midY);//Y
		vRIGHT.subtract(vUP);//Z
		pc.add(vRIGHT);
		Vector myVec=new Vector(pc);
		myVec.subtract(new Vector(p3d));
		myVec.normalize();
		Ray  myray1=new Ray(pc.getHead(), myVec);	
		theArry.add(myray1);
		
		
		Vector vRIGHT2=new Vector(VRIGHT);
		Vector vUP2=new Vector(VUP);
		double midX2=(x-(nx/2))*rx+0.01;//left down
		double midY2=(y-(ny/2))*ry+0.01;//left down
		Vector pc2=new Vector(new PointD3(new Coordinate(0), new Coordinate(0), new Coordinate(-screenDist)));
		vRIGHT2.scale(midX2);//X
		vUP2.scale(midY2);//Y
		vRIGHT2.subtract(vUP2);//Z
		pc2.add(vRIGHT2);
		Vector myVec2=new Vector(pc2);
		myVec2.subtract(new Vector(p3d));
		myVec2.normalize();
		Ray  myray2=new Ray(pc2.getHead(), myVec2);	
		theArry.add(myray2);
		
		Vector vRIGHT3=new Vector(VRIGHT);
		Vector vUP3=new Vector(VUP);
		double midX3=(x-(nx/2))*rx+rx-0.01;//right up
		double midY3=(y-(ny/2))*ry+ry-0.01;//right up
		Vector pc3=new Vector(new PointD3(new Coordinate(0), new Coordinate(0), new Coordinate(-screenDist)));
		vRIGHT3.scale(midX3);//X
		vUP3.scale(midY3);//Y
		vRIGHT3.subtract(vUP3);//Z
		pc3.add(vRIGHT3);
		Vector myVec3=new Vector(pc3);
		myVec3.subtract(new Vector(p3d));
		myVec3.normalize();
		Ray  myray3=new Ray(pc3.getHead(), myVec3);	
		theArry.add(myray3);
		
		Vector vRIGHT4=new Vector(VRIGHT);
		Vector vUP4=new Vector(VUP);
		double midX4=(x-(nx/2))*rx+rx-0.01;//right down
		double midY4=(y-(ny/2))*ry+0.01;//right dow
		Vector pc4=new Vector(new PointD3(new Coordinate(0), new Coordinate(0), new Coordinate(-screenDist)));
		vRIGHT4.scale(midX4);//X
		vUP4.scale(midY4);//Y
		vRIGHT4.subtract(vUP4);//Z
		pc4.add(vRIGHT4);
		Vector myVec4=new Vector(pc4);
		myVec4.subtract(new Vector(p3d));
		myVec4.normalize();
		Ray  myray4=new Ray(pc4.getHead(), myVec4);	
		theArry.add(myray4);
		
		Vector vRIGHT5=new Vector(VRIGHT);
		Vector vUP5=new Vector(VUP);
		double midX5=(x-(nx/2))*rx+0.01;//left up
		double midY5=(y-(ny/2))*ry+ry-0.01;//left up
		Vector pc5=new Vector(new PointD3(new Coordinate(0), new Coordinate(0), new Coordinate(-screenDist)));
		vRIGHT5.scale(midX5);//X
		vUP5.scale(midY5);//Y
		vRIGHT5.subtract(vUP5);//Z
		pc5.add(vRIGHT5);
		Vector myVec5=new Vector(pc5);
		myVec5.subtract(new Vector(p3d));
		myVec5.normalize();
		Ray  myray5=new Ray(pc5.getHead(), myVec5);	
		theArry.add(myray5);
		return theArry;
	}


}
