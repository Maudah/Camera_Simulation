package test;
import geometries.*;
import elements.*;

import primitives.*;
import org.junit.Test;

import Renderer.ImageWriter;
import Renderer.Render;
import Scene.Scene;


import java.awt.Color;

public class Picture2 {

	@Test
	public void test() {
		Scene scene = new Scene();
		scene.setSceenDistance(80);
		Sphere sphere0 = new Sphere (new PointD3(0.0, 0.0, -1000),700,new Color(222, 0, 0));//red
		Sphere sphere1 = new Sphere (new PointD3(-1200, 0.0, -1500),700,new Color(0, 229, 0));//green
		Sphere sphere2 = new Sphere (new PointD3(-2500, 0.0, -2000),700,new Color(0, 0, 203));//blue
		Sphere sphere3 = new Sphere (new PointD3(1200, 0.0, -1500),700,new Color(255, 255, 0));//yellow	
		Sphere sphere4 = new Sphere (new PointD3(2500, 0.0, -2000),700,new Color(255, 128, 0));//orange
		Material m=new Material();
		m.set_n(20);
		
		m.set_Ks(1);
		m.set_Kd(1);
		sphere0.setMaterial(m);
		sphere1.setMaterial(m);
		sphere2.setMaterial(m);
		sphere3.setMaterial(m);
		sphere4.setMaterial(m);
		
		scene.AddGeomety(sphere0);
		scene.AddGeomety(sphere1);
		scene.AddGeomety(sphere2);
		scene.AddGeomety(sphere3);
		scene.AddGeomety(sphere4);
		scene.AddLight(new PointLight(new Color(255,100,100), new PointD3(-200,200,100),  0, 0.00001, 0.000005));
		
		ImageWriter imageWriter = new ImageWriter("Picture1", 500, 500, 500, 500);
		Render render = new Render(scene,imageWriter);
		render.renderImage();
		render.getRenderImageWrite().writeToimage();
		
	}

}
