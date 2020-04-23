package test;



import java.awt.Color;

import org.junit.Test;

import Renderer.ImageWriter;
import Renderer.Render;
import Scene.Scene;
import elements.SpotLight;
import elements.camera;
import geometries.Sphere;

import primitives.Coordinate;
import primitives.Material;
import primitives.PointD3;
import primitives.Vector;

public class recursiveTest {

	@Test
	public void test() {
		Scene myscene = new Scene();
		myscene.setSceenDistance(300);
		Sphere sphere = new Sphere( new PointD3(0.0, 0.0, -1000),500,new Color(0, 0, 100));		
		Material m=new Material();
		m.set_n(20);
		m.set_kt(0.3);	
		m.set_Ks(0.5);
		m.set_Kd(0.5);

		sphere.setMaterial(m);
		myscene.AddGeomety(sphere);
		
		Sphere sphere1 = new Sphere( new PointD3(100, 0.0, -1000),250,new Color(100, 20, 20));		
		Material m1=new Material();
		m1.set_n(20);
		m1.set_kt(0);
		m1.set_Ks(1);
		m1.set_Kd(1);

		sphere1.setMaterial(m1);
		myscene.AddGeomety(sphere1);
		
		myscene.AddLight(new SpotLight(new Color(255, 100, 100), new PointD3(-200, -200, -150), 0.1, 0.00001, 0.000005,new Vector(2, 2, -3)));
		myscene.AddLight(new SpotLight(new Color(255,100,100), new PointD3(-200, -200, -100),  0, 0.00001, 0.000005,new Vector(2, 2, -3)));
		
		
		PointD3 p = new PointD3 (new Coordinate(0.0), new Coordinate(0.0), new Coordinate(0));
		PointD3 p1 = new PointD3(new Coordinate(0.0), new Coordinate(1.0), new Coordinate(0.0));
		PointD3 p2 = new PointD3(new Coordinate(1.0), new Coordinate(0.0), new Coordinate(0.0));
			
		Vector v1 = new Vector(p1);
		Vector v2 = new Vector(p2);			
		camera camera = new camera(p, v1, v2);
		
		ImageWriter imageWriter = new ImageWriter("Recursive Test", 500, 500, 500, 500);
		myscene.setColorScene(new Color(0, 0, 0));
		
		myscene.setSceneCamera(camera);
		Render render=new Render(myscene, imageWriter);
		render.renderImage();
		render.getRenderImageWrite().writeToimage();
				
	}

}
