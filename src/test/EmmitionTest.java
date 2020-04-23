package test;


import java.awt.Color;

import org.junit.Test;

import Renderer.ImageWriter;
import Renderer.Render;
import Scene.Scene;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Material;
import primitives.PointD3;

public class EmmitionTest {

	@Test
	public void test() {
		Scene scene = new Scene();
		scene.setSceenDistance(110);
		Material m=new Material();
		Sphere sphere = new Sphere (new PointD3(0.0, 0.0, -149),50,new Color(255, 0, 0));//red

		Triangle triangle = new Triangle(new PointD3( 100, 0, -149),
				new PointD3(  0, 100, -149),
				new PointD3( 100, 100, -149),
				new Color(0,255,0));

		Triangle triangle2 = new Triangle(new PointD3( 100, 0, -149),
				new PointD3(  0, -100, -149),
				new PointD3( 100,-100, -149),
				new Color(0,0,255));

		Triangle triangle3 = new Triangle(new PointD3(-100, 0, -149),
				new PointD3(  0, 100, -149),
				new PointD3(-100, 100, -149),
				new Color(255,255,0));

		Triangle triangle4 = new Triangle(new PointD3(-100, 0, -149),
				new PointD3(  0,  -100, -149),
				new PointD3(-100, -100, -149),
				new Color(255,0,255));
		sphere.setMaterial(m);
		triangle.setMaterial(m);
		triangle2.setMaterial(m);
		triangle4.setMaterial(m);
		triangle3.setMaterial(m);


		scene.AddGeomety(triangle);
		scene.AddGeomety(triangle2);
		scene.AddGeomety(triangle3);
		scene.AddGeomety(triangle4);
		scene.AddGeomety(sphere);

		ImageWriter imageWriter = new ImageWriter("Emmition test", 500, 500, 500, 500);	
		Render render = new Render( scene,imageWriter);	
		render.renderImage();
		render.printGrid(50);
		render.getRenderImageWrite().writeToimage();
	}

}
