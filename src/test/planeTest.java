package test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Test;

import elements.camera;
import geometries.Plane;
import primitives.Coordinate;
import primitives.PointD3;
import primitives.Ray;
import primitives.Vector;

public class planeTest extends Plane {

	@Test
	public void test() {

		final int WIDTH  = 3;
		final int HEIGHT = 3;

		Ray[][] rays = new Ray [HEIGHT][WIDTH];

		Coordinate c0 = new Coordinate(0.0);
		Coordinate c1 = new Coordinate(1.0);
		Coordinate cminus = new Coordinate(-1.0); 
		Coordinate cminus3 = new Coordinate(-3.0);		
		Coordinate cminusquarter = new Coordinate(-0.25);

		PointD3 p010 = new PointD3(c0, c1, c0);
		PointD3 p00m1 = new PointD3(c0, c0, cminus);
		PointD3 p00m3 = new PointD3(c0, c0, cminus3);
		PointD3 p0minqrtmin1 = new PointD3(c0,cminusquarter, cminus);

		camera camera = new elements.camera(new PointD3(c0 ,c0 ,c0),
				new Vector (p010),
				new Vector (p00m1));

		// plane orthogonal to the view plane
		Vector myvec=new Vector(p00m1);

		Plane plane = new Plane( new PointD3 (p00m3),myvec	,Color.black);

		// 45 degrees to the view plane

		Plane plane2 = new Plane( new PointD3 (p00m3),new Vector(p0minqrtmin1),Color.GREEN);

		ArrayList<PointD3> intersectionPointsPlane = new ArrayList<PointD3>();		
		ArrayList<PointD3> intersectionPointsPlane2 = new ArrayList<PointD3>();
		for (int i = 0; i < HEIGHT; i++){
			for (int j = 0; j < WIDTH; j++){				
				rays[i][j] = camera.ConstructRayThroughPixel(WIDTH, HEIGHT,j, i, 1, 3 * WIDTH, 3 * HEIGHT);				

				ArrayList<PointD3> rayIntersectionPoints   = plane. FindIntersections(rays[i][j]);

				ArrayList<PointD3> rayIntersectionPoints2  = plane2.FindIntersections(rays[i][j]);

				for (PointD3 iPoint: rayIntersectionPoints)
					intersectionPointsPlane.add(iPoint);

				for (PointD3 iPoint: rayIntersectionPoints2)
					intersectionPointsPlane2.add(iPoint);	
			}
		}

		assertTrue(intersectionPointsPlane. size() == 9);
		assertTrue(intersectionPointsPlane2.size() == 9);

		System.out.println("plane1");
		System.out.println(plane);

		for (PointD3 iPoint: intersectionPointsPlane)
			System.out.println(iPoint);

		System.out.println("---");

		System.out.println("plane2");
		System.out.println(plane2);

		for (PointD3 iPoint: intersectionPointsPlane2)
			System.out.println(iPoint);
	}


}
