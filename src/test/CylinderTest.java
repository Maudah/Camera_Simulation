package test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Test;

import elements.camera;
import geometries.Sphere;
import primitives.Coordinate;
import primitives.PointD3;
import primitives.Ray;
import primitives.Vector;

public class CylinderTest extends Sphere {

	@Test
	public void test() 
	{
		Coordinate c0 = new Coordinate(0.0);
		Coordinate c1 = new Coordinate(1.0);
		Coordinate cminus = new Coordinate(-1.0); 
		Coordinate cminus3 = new Coordinate(-3.0);

		PointD3 p010 = new PointD3(c0, c1, c0);
		PointD3 p00m1 = new PointD3(c0, c0, cminus);
		PointD3 p00m3 = new PointD3(c0, c0, cminus3);

		final int WIDTH  = 3;
		final int HEIGHT = 3;

		Ray[][] rays = new Ray [HEIGHT][WIDTH];

		camera camera = new camera(new PointD3(c0 ,c0 ,c0),
				new Vector(p010),
				new Vector (p00m1));

		Sphere sphere  = new Sphere(new PointD3(p00m3),1,new Color(255,0,0));
		Sphere sphere2 = new Sphere(  new PointD3(p00m3),10,new Color(255,0,0));

		// Only the center ray intersect the sphere in two locations
		ArrayList<PointD3> intersectionPointsSphere = new ArrayList<PointD3>();

		// The sphere encapsulates the view plane - all rays intersect with the sphere once
		ArrayList<PointD3> intersectionPointsSphere2 = new ArrayList<PointD3>();

		System.out.println("Camera:\n" + camera);

		for (int i = 0; i < HEIGHT; i++){
			for (int j = 0; j < WIDTH; j++){

				rays[i][j] = camera.ConstructRayThroughPixel(WIDTH, HEIGHT, j, i, 1, 3*WIDTH, 3*HEIGHT);			
				ArrayList<PointD3> rayIntersectionPoints  = sphere. FindIntersections(rays[i][j]);
				ArrayList<PointD3> rayIntersectionPoints2 = sphere2.FindIntersections(rays[i][j]);
				if(rayIntersectionPoints!=null)
					for (PointD3 iPoint: rayIntersectionPoints)
						intersectionPointsSphere.add(iPoint);
				if(rayIntersectionPoints2!=null)
					for (PointD3 iPoint: rayIntersectionPoints2)
						intersectionPointsSphere2.add(iPoint);

			}
		}
		assertEquals(2, intersectionPointsSphere. size());

		System.out.println("Intersection Points:");
		for (PointD3 iPoint: intersectionPointsSphere){

			Coordinate cminus2 = new Coordinate(-2.0); 
			Coordinate cminus4 = new Coordinate(-4.0);
			PointD3 p00m2 = new PointD3(c0, c0, cminus2);
			PointD3 p00m4 = new PointD3(c0, c0, cminus4);

			assertTrue(iPoint.equals(p00m2)|| 
					iPoint.equals(p00m4) );

			System.out.println(iPoint);
		}
	}




}
