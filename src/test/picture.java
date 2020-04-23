package test;


import java.awt.Color;

import org.junit.Test;

import Renderer.ImageWriter;
import Renderer.Render;
import Scene.Scene;
import elements.PointLight;
import elements.camera;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Coordinate;
import primitives.Material;
import primitives.PointD3;
import primitives.Vector;

public class picture {

	@Test
	public void test() {


		Scene scene = new Scene();
		Material m=new Material();
		m.set_n(20);
		m.set_Ks(0.2);
		m.set_Kd(0.2);
		Triangle triangle = new Triangle(new PointD3(  -1000,  -1000, -1100),
				new PointD3( -1000, -2000, -1100),
				new PointD3(2000, -1500, -1150),
				new Color(202, 131, 84));

		Triangle triangle2 = new Triangle(new PointD3(   -1000,  -1000, -1100),
				new PointD3( -600, -700, -1500),
				new PointD3( 2000, -1500, -1150),
				new Color(202, 131, 84));
		Sphere sphere = new Sphere(new PointD3(100, 100, -1200),700,new Color(202, 241, 84));

		triangle.setMaterial(m);
		triangle2.setMaterial(m);

		m.set_Ks(0.5);
		m.set_Kd(0.5);

		sphere.setMaterial(m);

		scene.AddGeomety(sphere);
		scene.AddGeomety(triangle);
		scene.AddGeomety(triangle2);

		scene.AddLight(new PointLight(new Color(50, 50, 50),new PointD3(300, 500, -0),0, 0.0000001, 0.00000005));


		PointD3 p = new PointD3 (new Coordinate(0.0), new Coordinate(0.0), new Coordinate(0));
		PointD3 p1 = new PointD3(new Coordinate(0.0), new Coordinate(-1.0), new Coordinate(0.0));
		PointD3 p2 = new PointD3(new Coordinate(1.0), new Coordinate(0.0), new Coordinate(0.0));

		Vector v1 = new Vector(p1);
		Vector v2 = new Vector(p2);

		camera camera = new camera(p, v1, v2);

		scene.setColorScene(new Color(0, 0, 0));


		scene.setSceenDistance(100);
		scene.setSceneCamera(camera);


		ImageWriter imageWriter = new ImageWriter("Picture1 ", 500, 500, 500, 500);

		Render render = new Render(scene,imageWriter);

		render.renderImage();
		render.getRenderImageWrite().writeToimage();
	}

}
