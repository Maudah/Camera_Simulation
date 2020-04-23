package test;



import java.awt.Color;

import org.junit.Test;

import Renderer.ImageWriter;
import Renderer.Render;
import Scene.Scene;
import elements.SpotLight;
import elements.camera;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Coordinate;
import primitives.Material;
import primitives.PointD3;
import primitives.Vector;

public class recursiveTest2 {

	@Test
	public void test() {


		Scene myscene = new Scene();
		myscene.setSceenDistance(300);
		Sphere sphere = new Sphere(new PointD3(-550, -500, -1000),300,new Color(0, 0, 100));		
		Material m=new Material();
		m.set_n(20);
		m.set_kt(0.5);	
		m.set_Ks(1);
		m.set_Kd(3);

		sphere.setMaterial(m);
		myscene.AddGeomety(sphere);
		
		Sphere sphere1 = new Sphere( new PointD3(-550, -500, -1000),150,new Color(100, 20, 20));		
		Material m1=new Material();
		m1.set_n(20);
		m1.set_kt(0);
		m1.set_Ks(1);
		m1.set_Kd(1);

		sphere1.setMaterial(m1);
		myscene.AddGeomety(sphere1);
		
		Triangle triangle = new Triangle(new PointD3(  1500, -1500, -1500),
				 new PointD3( -1500,  1500, -1500),
				 new PointD3(  200,  200, -375),
				new Color(20, 20, 20));

		Triangle triangle2 = new Triangle(new PointD3(  1500, -1500, -1500),
						  new PointD3( -1500,  1500, -1500),
						  new PointD3( -1500, -1500, -1500),
						  new Color(20, 20, 20));
		
		Material m3=new Material();
		Material m4=new Material();
		m3.set_kr(1);
		m4.set_kr(1);
		m3.set_n(20);
	
		m3.set_Ks(2);
		m3.set_Kd(2);
		m4.set_n(20);
	
		m4.set_Ks(2);
		m4.set_Kd(2);
		triangle.setMaterial(m3);
		triangle2.setMaterial(m4);
	    myscene.AddGeomety(triangle);
		myscene.AddGeomety(triangle2);
				
				
		myscene.AddLight(new SpotLight(new Color(255, 100, 100),  new PointD3(200, 200, -100), 0, 0.00001, 0.000005 ,new Vector(-2, -2, -3)));
	
		
		
		PointD3 p = new PointD3 (new Coordinate(0.0), new Coordinate(0.0), new Coordinate(0));
		PointD3 p1 = new PointD3(new Coordinate(0.0), new Coordinate(1.0), new Coordinate(0.0));
		PointD3 p2 = new PointD3(new Coordinate(1.0), new Coordinate(0.0), new Coordinate(0.0));
			
		Vector v1 = new Vector(p1);
		Vector v2 = new Vector(p2);			
		camera camera = new camera(p, v1, v2);
		System.out.println("Camera:\n" + camera.getP3d());
		ImageWriter imageWriter = new ImageWriter("Recursive Test1", 500, 500, 500, 500);
		myscene.setColorScene(new Color(0, 0, 0));
		
		myscene.setSceneCamera(camera);
		Render render=new Render(myscene, imageWriter);
		render.renderImage();
		render.getRenderImageWrite().writeToimage();
				
	}

}
