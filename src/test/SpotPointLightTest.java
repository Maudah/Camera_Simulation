package test;


import java.awt.Color;

import org.junit.Test;

import Renderer.ImageWriter;
import Renderer.Render;
import Scene.Scene;


import elements.*;
import geometries.Sphere;
import primitives.Material;
import primitives.PointD3;
import primitives.Vector;


public class SpotPointLightTest {

	@Test
	public void test() {
		Scene scene = new Scene();
		Sphere sphere = new Sphere ( new PointD3(0.0, 0.0, -1000),800,new Color(0, 0, 100));
		Material m=new Material();
		m.set_n(20);
		m.set_Ks(1);
		m.set_Kd(1);
		sphere.setMaterial(m);
		scene.AddGeomety(sphere);
		scene.AddLight(new SpotLight(new Color(255,100,100), new PointD3(-200, -200, 100),  0, 0.00001, 0.000005,new Vector(2, 2, -3)));
		
	
		ImageWriter imageWriter = new ImageWriter("spot light test", 500, 500, 500, 500);
	
		Render render = new Render(scene,imageWriter);
		
		render.renderImage();
		render.getRenderImageWrite().writeToimage();
	}

}
