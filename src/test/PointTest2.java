package test;

import java.awt.Color;

import org.junit.Test;

import Renderer.ImageWriter;
import Renderer.Render;
import Scene.Scene;
import elements.PointLight;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Material;
import primitives.PointD3;

public class PointTest2 {

	@Test
	public void test() {
		Scene scene = new Scene();
		scene.setSceenDistance(100);
		Sphere sphere = new Sphere (new PointD3(0.0, 0.0, -1000),500,new Color(0, 0, 100));//red


		scene.AddGeomety(sphere);
		Material m=new Material();
		m.set_n(20);
		sphere.setMaterial(m);

		Triangle triangle = new Triangle(new PointD3(  -3500,  3500, -2000),
				new PointD3( 3500, -3500, -1000),
				new PointD3(  3500, 3500, -2000),
				new Color(0,0,0));

		Triangle triangle2 = new Triangle(new PointD3(  -3500,  3500, -2000),
				new PointD3( -3500,  -3500, -1000),
				new PointD3( 3500, -3500, -1000),
				new Color(0,0,0));
		triangle.setMaterial(m);
		triangle2.setMaterial(m);
		scene.AddGeomety(triangle);
		scene.AddGeomety(triangle2);

		scene.AddLight(new PointLight(new Color(255, 100, 100), new PointD3(200, 200, -100), 
				0, 0.000001, 0.0000005));


		ImageWriter imageWriter = new ImageWriter("Point test 2", 500, 500, 500, 500);
		Render render = new Render(scene,imageWriter);		
		render.renderImage();
		render.getRenderImageWrite().writeToimage();
	}

}
