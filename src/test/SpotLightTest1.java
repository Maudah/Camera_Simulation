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
public class SpotLightTest1 {

	@Test
	public void test() {

		Scene scene = new Scene();
		Material m=new Material();
		m.set_n(20);
		m.set_Ks(1);
		m.set_Kd(1);
		Triangle triangle = new Triangle(new PointD3(  3500,  3500, -2000),
				new PointD3( -3500, -3500, -1000),
				new PointD3(  3500, -3500, -2000),
				new Color(0,0,0));

		Triangle triangle2 = new Triangle(new PointD3(  3500,  3500, -2000),
				new PointD3( -3500,  3500, -1000),
				new PointD3( -3500, -3500, -1000),
				new Color(0,0,0));
		Sphere sphere = new Sphere(new PointD3(0, 500.0, -1000),500,new Color(0, 0, 100));



		triangle.setMaterial(m);
		triangle2.setMaterial(m);
		m.set_Ks(1);
		m.set_Kd(1);
		sphere.setMaterial(m);
		scene.AddGeomety(sphere);
		scene.AddGeomety(triangle);
		scene.AddGeomety(triangle2);

		scene.AddLight(new PointLight(new Color(255, 100, 100),new PointD3(200, 200, -100),0, 0.000001, 0.0000005));


		PointD3 p = new PointD3 (new Coordinate(0.0), new Coordinate(0.0), new Coordinate(0));
		PointD3 p1 = new PointD3(new Coordinate(0.0), new Coordinate(-1.0), new Coordinate(0.0));
		PointD3 p2 = new PointD3(new Coordinate(1.0), new Coordinate(0.0), new Coordinate(0.0));

		Vector v1 = new Vector(p1);
		Vector v2 = new Vector(p2);

		camera camera = new camera(p, v1, v2);

		scene.setColorScene(new Color(0, 0, 0));


		scene.setSceenDistance(100);
		scene.setSceneCamera(camera);


		ImageWriter imageWriter = new ImageWriter("Spot test plane4 ", 500, 500, 500, 500);

		Render render = new Render(scene,imageWriter);

		render.renderImage();
		render.getRenderImageWrite().writeToimage();
	}

}
