package Scene;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import elements.AmbientLight;
import elements.Light;

//import javax.swing.text.StyledEditorKit.ForegroundAction;

import elements.camera;
import geometries.Geometry;
//import primitives.PointD3;
//import primitives.Vector;

public class Scene {
	AmbientLight ambientLight;
	ArrayList<Light> lights;
	String nameScene;
	Color colorScene;
	ArrayList<Geometry> geoList;
	camera sceneCamera;
	double sceenDistance;
	
	//Constructors
	public Scene(String nameScene, Color colorScene, ArrayList<Geometry> geoList, camera sceneCamera,
			double sceenDistance,AmbientLight ambientLight,ArrayList<Light> lights) {
		super();
		this.nameScene = nameScene;
		this.colorScene = colorScene;
		this.geoList = geoList;
		this.sceneCamera =new camera(sceneCamera);
		this.sceenDistance = sceenDistance;
		this.ambientLight=new AmbientLight(ambientLight);
		geoList=new ArrayList<Geometry>();
		lights=new ArrayList<Light>();
		for(Light item:lights)
			lights.add(item);
		for(Geometry myGeo:geoList)
			geoList.add(myGeo);
	}
	public Scene() {
		super();
		this.nameScene = "some";
		this.colorScene = new Color(0,0,0);
		this.geoList = new ArrayList<Geometry>();
		this.sceneCamera =new camera();
		this.sceenDistance = 150;
		this.ambientLight=new AmbientLight();
		this.lights=new ArrayList<Light>();
		
	}
	public Scene(Scene myScene)
	{
		this.nameScene = myScene.getNameScene();
		this.colorScene = myScene.getColorScene();
		this.geoList =new ArrayList<Geometry>();
		this.lights=new ArrayList<Light>();
		this.sceneCamera =new camera(myScene.getSceneCamera());
		this.sceenDistance =myScene.getSceenDistance();
		this.ambientLight=new AmbientLight(myScene.ambientLight);
		for(Geometry geo:myScene.getGeoList())
			this.geoList.add(geo);
		for(Light item:myScene.lights)
			this.lights.add(item);
		
	}
	
	//getter and setter
	public AmbientLight getAmbientLight() {
		return ambientLight;
	}
	public void setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
	}
	public ArrayList<Light> getLights() {
		return lights;
	}
	public void setLights(ArrayList<Light> lights) {
		this.lights = lights;
	}
	
	public String getNameScene() {
		return nameScene;
	}
	public void setNameScene(String nameScene) {
		this.nameScene = nameScene;
	}
	public Color getColorScene() {
		return colorScene;
	}
	public void setColorScene(Color colorScene) {
		this.colorScene = colorScene;
	}
	public ArrayList<Geometry> getGeoList() {
		return geoList;
	}
	public void setGeoList(ArrayList<Geometry> geoList) {
		this.geoList = geoList;
	}
	public camera getSceneCamera() {
		return sceneCamera;
	}
	public void setSceneCamera(camera sceneCamera) {
		this.sceneCamera = sceneCamera;
	}
	public double getSceenDistance() {
		return sceenDistance;
	}
	public void setSceenDistance(double sceenDistance) {
		this.sceenDistance = sceenDistance;
	}
	public  boolean equals(Object s)
	{		
		Scene s1=(Scene)s;
		if(s1.getColorScene()==colorScene&&s1.getNameScene()==nameScene&&s1.getSceenDistance()==sceenDistance&&s1.getSceneCamera().equals(sceneCamera)&&geoList.size()==s1.getGeoList().size())
		{
			boolean flage=false;
			for (Geometry geo: s1.getGeoList()){
				for(Geometry geo1: getGeoList())
				{
					if(geo.equals(geo1)==true)
						flage=true;
				}
				if(flage==false)
					return false;
			}
			return true;
		}
		return false;
	}
	public void AddLight(Light light)
	{
		lights.add(light);
	}
	public void AddGeomety(Geometry geometry)
	{
		geoList.add(geometry);
	}
	public Iterator<Light> GetLightIterator()
	{
		return lights.iterator();
	}
	public String toString()
	{
		String str="";
		for(Geometry mygeo:geoList)
			str+=" ,"+mygeo;
		return "distance: " + sceenDistance+ ", color="+colorScene+"camera: "+sceneCamera+" list: "+str;
	}
	public Iterator<Geometry> getGeometriesIterator()
	{

		return geoList.iterator();

	}

}
