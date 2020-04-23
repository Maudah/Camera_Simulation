package Renderer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import Scene.Scene;
import elements.Light;
import geometries.FlatGeometry;
import geometries.Geometry;
import primitives.PointD3;
import primitives.Ray;
import primitives.Vector;
import java.util.*;
public class Render {
	Scene renderScene;
	ImageWriter renderImageWrite;
	public int RECURSION_LEVEL =3;

	//getter and setter
	public Scene getRenderScene() {
		return renderScene;
	}
	public void setRenderScene(Scene renderScene) {
		this.renderScene = renderScene;
	}
	public ImageWriter getRenderImageWrite() {
		return renderImageWrite;
	}
	public void setRenderImageWrite(ImageWriter renderImageWrite) {
		this.renderImageWrite = renderImageWrite;
	}
	//constructor
	public Render(Scene renderScene, ImageWriter renderImageWrite) {
		super();
		this.renderScene =new Scene( renderScene);
		this.renderImageWrite =new ImageWriter( renderImageWrite);
	}
	//copy constructor
	public Render(Render r)
	{
		super();
		this.renderScene =new Scene(r.getRenderScene());
		this.renderImageWrite =new ImageWriter(r.getRenderImageWrite());
	}

	public boolean equals(Object r)
	{
		Render r1=new Render((Render)r);
		if(r1.getRenderScene().equals(getRenderScene()))
			if(r1.getRenderImageWrite().equals(getRenderImageWrite()))
				return true;
		return false;
	}
	public String toString()
	{
		String s1=this.getRenderScene().toString();
		String s2=this.getRenderImageWrite().toString();
		return "scene: "+s1+"image: "+s2;
	}
	public void printGrid(int interval)
	{
		for(int i=1;i<renderImageWrite.getNx()-1;i++)
		{
			for(int j=1;j<renderImageWrite.getNy()-1;j++)
			{
				if(i%interval==0||j%interval==0)
					renderImageWrite.writePixel(i, j, 255,255,255);				
			}
		}
		renderImageWrite.writeToimage();
	}

	//Create the picture
	public void renderImage()

	{	
		int r=0,g=0,b=0;
		for(int i=0;i<renderImageWrite.getNx();i++)//all pixels across the screen
		{			
			for(int j=0;j<renderImageWrite.getNy();j++)//all pixels along the screen
			{
				r=0;
				g=0;
				b=0;
				//Builds rays that pass through this pixel
				ArrayList<Ray> rays = renderScene.getSceneCamera().ConstructRayThroughPixel1(renderImageWrite.getNx(),
						renderImageWrite.getNy(), i, j,renderScene.getSceenDistance(), renderImageWrite.getWidth(), 
						renderImageWrite.getHeight());

				/////////////////////
				//without improvment
				/*ArrayList<Ray> rays = renderScene.getSceneCamera().ConstructRayThroughPixel2(renderImageWrite.getNx(),
				renderImageWrite.getNy(), i, j,renderScene.getSceenDistance(), renderImageWrite.getWidth(), 
				renderImageWrite.getHeight());*/
				///////////////////////


				for (Iterator<Ray> iterator = rays.iterator(); iterator.hasNext();) //עבור כל קרן שבתוך הפיקסל
				{
					Ray r1 = iterator.next();
					Map<Geometry,ArrayList<PointD3>> intersectionPoints=getSceneRayIntersectios(r1);
					if (intersectionPoints.isEmpty()==false)
					{
						Map<Geometry,PointD3> c= getClosestPoint(intersectionPoints);//הנקודה שצריך לצבוע
						for(Entry<Geometry, PointD3> entry:c.entrySet())
						{

							Color c1=calcColor(entry.getKey(),entry.getValue(),r1);//חישוב הצבע בנקודה
							r+=c1.getRed();
							g+=c1.getGreen();
							b+=c1.getBlue();

						}				 				 
					}
					intersectionPoints.clear();			
				}

				/////////////////////
				//without improvment
				//renderImageWrite.writePixel(i,j,new Color(r, g, b));
				//////////////////////

				renderImageWrite.writePixel(i,j,new Color(r/5, g/5, b/5));
			}						
		}	
	}

	//getting a ray returns a map of any geometry with its intersection points
	private Map<Geometry,ArrayList<PointD3>> getSceneRayIntersectios(Ray ray)
	{
		java.util.Iterator<Geometry> geometries=renderScene.getGeometriesIterator();
		HashMap<Geometry,ArrayList<PointD3>> theMap=new HashMap<Geometry,ArrayList<PointD3>>();
		ArrayList<PointD3> intersectionPoints = new ArrayList<PointD3>();
		ArrayList<PointD3> geometryIntersectionPoints;
		while(geometries.hasNext())
		{				
			Geometry geometry = geometries.next();				
			geometryIntersectionPoints = geometry.FindIntersections(ray);		      
			if(geometryIntersectionPoints!=null&&geometryIntersectionPoints.size()>0)
			{					
				intersectionPoints.addAll(geometryIntersectionPoints);
				theMap.put(geometry, geometryIntersectionPoints);
			}
		}
		return theMap;
	}

	//returns the closest point
	private Map<Geometry,PointD3> getClosestPoint(Map<Geometry,ArrayList<PointD3>> intersectionPoints)
	{
		HashMap<Geometry,PointD3> themap=new HashMap<Geometry,PointD3>();

		double distance = Double.MAX_VALUE;
		PointD3 p0 = renderScene.getSceneCamera().getP3d();
		for(Entry<Geometry, ArrayList<PointD3>> entry:intersectionPoints.entrySet())
		{				
			for(int i=0; i< entry.getValue().size(); i++)
			{
				if(p0.Distance(entry.getValue().get(i))<distance)
				{
					themap.clear();
					themap.put(entry.getKey(), entry.getValue().get(i));						
					distance = p0.Distance(entry.getValue().get(i));
				}
			}
		}
		return themap;
	}

	//returns the second closest point
	private Map<Geometry,PointD3> getClosestPointForRef(Map<Geometry,ArrayList<PointD3>> intersectionPoints,PointD3 p)
	{
		HashMap<Geometry,PointD3> themap=new HashMap<Geometry,PointD3>();			
		double distance = Double.MAX_VALUE;
		double distance1 = Double.MAX_VALUE;
		for(Entry<Geometry, ArrayList<PointD3>> entry:intersectionPoints.entrySet())
		{	

			for(int i=0; i< entry.getValue().size(); i++)
				if(p.Distance(entry.getValue().get(i))<distance)						
					distance = p.Distance(entry.getValue().get(i));				
		}
		for(Entry<Geometry, ArrayList<PointD3>> entry:intersectionPoints.entrySet())								
			for(int i=0; i< entry.getValue().size(); i++)
				if(p.Distance(entry.getValue().get(i))<distance1&&p.Distance(entry.getValue().get(i))!=distance)
				{
					themap.clear();
					themap.put(entry.getKey(), entry.getValue().get(i));						
					distance1 = p.Distance(entry.getValue().get(i));
				}				
		return themap;
	}


	private Color calcColor(Geometry geometry,PointD3 pd3,Ray inRay)
	{
		return calcColor( geometry, pd3, inRay,0);
	}

	//calculate Pixel color
	private Color calcColor(Geometry geometry,PointD3 pd3,Ray inRay,int level)
	{
		if(level==RECURSION_LEVEL)
			return new Color(0, 0, 0);
		Color diffuse=new Color(0, 0, 0);				
		double d=0,s=0;
		Color specular=new Color(0, 0, 0);	
		Color refractedLight=new Color(0, 0, 0);
		Color reflectedLight=new Color(0, 0, 0);
		Color ambient=this.renderScene.getAmbientLight().getIntensity(pd3);
		Color emmision=geometry.getEmmision();
		Iterator<Light> lights=renderScene.GetLightIterator();	

		Ray reflectedRay=new Ray();
		Color reflectedColor = new Color(0,0,0);
		double kr = geometry.getMaterial().get_kr();
		double	kt = geometry.getMaterial().get_kt();
		Color refractedColor = new Color(0, 0, 0);
		while(lights.hasNext())//עבור על התאורות
		{
			Light lightFromIterator=lights.next();
			Vector n=new Vector(geometry.getNormal(pd3));
			Vector l=new Vector(lightFromIterator.getL(pd3));
			Vector v=new Vector(renderScene.getSceneCamera().getP3d());
			v.subtract(new Vector(pd3));
			boolean f1=occluded(lightFromIterator, pd3, geometry);					
			if(f1==false)
			{	
				double td=CalcDiffuseCom(geometry.getMaterial().get_Kd(),n,l);	
				d+=td;
				d=Math.abs(d);				
				double ts=CalSpecularComp(geometry.getMaterial().get_Ks(),geometry.getMaterial().get_n(),v,geometry.getNormal(pd3),lightFromIterator.getL(pd3));
				s+=	ts;
				s=Math.abs(s);
				specular=SumOfColors(lightFromIterator.getIntensity(pd3), s);
				diffuse=SumOfColors(lightFromIterator.getIntensity(pd3), d);
			}
			if(kr!=0)
			{
				reflectedRay = constructReflectedRay1(geometry.getNormal(pd3), pd3, inRay);
				Map<Geometry, PointD3>	reflectedEntry =getClosestPoint(getSceneRayIntersectios1(reflectedRay));  	
				for (Entry<Geometry, PointD3> entry: reflectedEntry.entrySet())	 
				{
					reflectedRay.setPoo(entry.getValue());
					reflectedColor = calcColor(entry.getKey(), entry.getValue(), reflectedRay,level+1);								
				}			   		 			
				reflectedLight = new Color ( (int)(reflectedColor.getRed()*kr),(int)(reflectedColor.getGreen()*kr),(int)(reflectedColor.getBlue()*kr));			    							

			}
			if(kt!=0)
			{
				Ray refractedRay = constructRefractedRay(geometry.getNormal(pd3), pd3, inRay);
				Map<Geometry, PointD3> refractedEntry = getClosestPointForRef(getSceneRayIntersectios(refractedRay),pd3);					
				for (Entry<Geometry, PointD3> entry: refractedEntry.entrySet())	  
					refractedColor = calcColor(entry.getKey(), entry.getValue(), refractedRay,level+1);		  
				refractedLight = new Color ((int)(refractedColor.getRed()*kt),(int)(refractedColor.getGreen()*kt),(int)(refractedColor.getBlue()*kt));	
			}
		}

		double r=diffuse.getRed()+specular.getRed()+ambient.getRed()+emmision.getRed()+refractedLight.getRed()+reflectedLight.getRed();
		double g=diffuse.getGreen()+specular.getGreen()+ambient.getGreen()+emmision.getGreen()+refractedLight.getGreen()+reflectedLight.getGreen();
		double b=diffuse.getBlue()+specular.getBlue()+ambient.getBlue()+emmision.getBlue()+refractedLight.getBlue()+reflectedLight.getBlue();
		r=Math.min(r, 255);
		g=Math.min(g, 255);
		b=Math.min(b, 255);
		if(r<0||g<0||b<0)
			System.out.println("error calccolor");
		return new Color((int)r, (int)g, (int)b);

	}

	private Ray constructRefractedRay(Vector normal, PointD3 pd3, Ray inRay) {
		return new Ray(new PointD3(pd3.getX().getX()-0.2,pd3.getY().getX()-0.2,pd3.getZ().getX()-0.2), inRay.getDirection());
	}



	//Calculate diffuse influence
	public double CalcDiffuseCom(double kd,Vector n,Vector l)
	{
		n.normalize();
		l.normalize();

		double d=l.dotProduct(n);
		d=Math.abs(d);
		double x=d*kd;
		return x;
	}


	//returns reflected ray
	public Ray constructReflectedRay1(Vector n, PointD3 point, Ray ray) 
	{
		n.normalize();
		Vector l=new Vector(ray.getDirection());
		l.normalize();
		double dot = (-2*l.dotProduct(n) );
		Vector no=new Vector(n);
		no.normalize();
		no.scale(dot);
		l.add(no);
		Vector r=new Vector(l);
		r.normalize();
		point.add(no);
		return new Ray(point,r);				
	}	
	//Calculate specular influence 
	public double CalSpecularComp(double ks,double n,Vector v,Vector normal,Vector d)
	{
		v.normalize();
		normal.normalize();
		d.normalize();
		Vector r=new Vector(d);			
		double temp=d.dotProduct(normal)*2;
		normal.scale(temp);
		normal.subtract(r);
		temp=v.dotProduct(normal);
		temp=Math.pow(temp, n);
		double x=Math.abs( temp*ks);
		return x;

	}

	//Multiplies each value in a color
	public Color SumOfColors(Color c1,double c2)
	{
		double r=c1.getRed()*c2;
		double g=c1.getGreen()*c2;
		double b=c1.getBlue()*c2;
		r=Math.min(r, 255);
		g=Math.min(g, 255);
		b=Math.min(b, 255);

		return new Color((int)r, (int)g, (int)b);
	}

	//return 1 if shadow exists and 0 else
	private boolean occluded(Light light, PointD3 point, Geometry geometry) {
		Vector lightDirection = light.getL(point);
		lightDirection.scale(-1);
		lightDirection.normalize();
		PointD3 geometryPoint = new PointD3(point);
		Vector epsVector = new Vector(geometry.getNormal(point));
		epsVector.scale(2);
		geometryPoint.add(epsVector);
		Ray lightRay = new Ray(geometryPoint, lightDirection);
		Map<Geometry, ArrayList<PointD3>> intersectionPoints =getSceneRayIntersectios1(lightRay);
		if (geometry instanceof FlatGeometry){
			intersectionPoints.remove(geometry);
		}
		for (Entry<Geometry, ArrayList<PointD3>> entry: intersectionPoints.entrySet())
			if (entry.getKey().getMaterial().get_kt() == 0)
				return true;
		return false;	
	}
	//Returns intersection points if the point differs from the ray's point
	private Map<Geometry,ArrayList<PointD3>> getSceneRayIntersectios1(Ray ray)
	{
		PointD3 p=new PointD3(ray.getPoo());
		java.util.Iterator<Geometry> geometries=renderScene.getGeometriesIterator();
		HashMap<Geometry,ArrayList<PointD3>> theMap=new HashMap<Geometry,ArrayList<PointD3>>();
		ArrayList<PointD3> intersectionPoints = new ArrayList<PointD3>();
		ArrayList<PointD3> geometryIntersectionPoints;
		while(geometries.hasNext())
		{				
			Geometry geometry = geometries.next();				
			geometryIntersectionPoints = geometry.FindIntersections(ray);		      
			if(geometryIntersectionPoints!=null&&geometryIntersectionPoints.size()>0&&geometryIntersectionPoints.get(0).equals(p)==false)
			{					
				intersectionPoints.addAll(geometryIntersectionPoints);
				theMap.put(geometry, geometryIntersectionPoints);
			}
		}
		return theMap;
	}
}