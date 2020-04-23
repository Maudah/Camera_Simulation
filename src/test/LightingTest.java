package test;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Test;


import geometries.*;
import primitives.Material;
import primitives.PointD3;

import Renderer.Render;
import Renderer.ImageWriter;
import Scene.Scene;

public class LightingTest {

	
	@Test 
	public void emmissionTest(){
		Material m=new Material();
		m.set_n(20);
		m.set_kt(1);
		m.set_Ks(1);
		m.set_Kd(1);
		Triangle triangle = new Triangle(new PointD3( 100, 0, -149),
				 						 new PointD3(  0, 100, -149),
				 						 new PointD3( 100, 100, -149),
				 						 new Color(255,255,255));
		
		Triangle triangle2 = new Triangle(new PointD3( 100, 0, -149),
				 			 			  new PointD3(  0, -100, -149),
				 			 			  new PointD3( 100,-100, -149),
					 						 new Color(255,255,255));
		
		Triangle triangle3 = new Triangle(new PointD3(-100, 0, -149),
				 						  new PointD3(  0, 100, -149),
				 						  new PointD3(-100, 100, -149),
				 						  new Color(255,255,255));
		
		Triangle triangle4 = new Triangle(new PointD3(-100, 0, -149),
				 			 			  new PointD3(  0,  -100, -149),
				 			 			  new PointD3(-100, -100, -149),
					 						 new Color(255,255,255));
		triangle.setMaterial(m);
		triangle2.setMaterial(m);
		triangle3.setMaterial(m);
		triangle4.setMaterial(m);
		Sphere sphere=new Sphere(new PointD3(0.0, 0.0, -150),50, new Color(255,255,255));
		sphere.setMaterial(m);
		ArrayList<Geometry> myList=new ArrayList<Geometry>();
		
		myList.add(triangle);
		myList.add(triangle2);
		myList.add(triangle3);
		myList.add(triangle4);
		myList.add(sphere);
 
		ImageWriter imageWriter = new ImageWriter("Emition1", 501, 501, 501, 501);
		Scene myscene=new Scene();
		 
		myscene.setGeoList(myList);
		myscene.setNameScene("Emition1Scene");
		myscene.setSceenDistance(90);
		Render render=new Render(myscene, imageWriter);
		
		render.renderImage();//create the picture
		render.getRenderImageWrite().writeToimage();
	}
	
	
	
	
	

}