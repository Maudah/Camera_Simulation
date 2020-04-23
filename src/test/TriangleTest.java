package test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;


import org.junit.Test;

import elements.camera;
import geometries.Triangle;
import primitives.Coordinate;
import primitives.PointD3;
import primitives.Ray;
import primitives.Vector;

public class TriangleTest extends Triangle {

	@Test
	public void test() {final int WIDTH  = 3;
	final int HEIGHT = 3;

	Ray[][] rays = new Ray [HEIGHT][WIDTH];


	Coordinate c0 = new Coordinate(0.0);
	Coordinate c1 = new Coordinate(1.0);
	Coordinate cminus = new Coordinate(-1.0); 

	Coordinate cminus2 = new Coordinate(-2.0); 

	Coordinate c10 = new Coordinate(10.0);



	PointD3 p010 = new PointD3(c0, c1, c0);
	PointD3 p00m1 = new PointD3(c0, c0, cminus);

	camera camera = new camera(new PointD3(c0 ,c0 ,c0),
			new Vector (p010),
			new Vector (p00m1));



	Triangle triangle = new Triangle(
			new PointD3(c0 ,c1 ,cminus2),
			new PointD3(c1 ,cminus ,cminus2),
			new PointD3(cminus ,cminus ,cminus2),Color.GREEN);


	Triangle triangle2 = new Triangle(
			new PointD3(c0 ,c10 ,cminus2),
			new PointD3(c1 ,cminus ,cminus2),
			new PointD3(cminus ,cminus ,cminus2),Color.GREEN);

	ArrayList<PointD3> intersectionPointsTriangle = new ArrayList<PointD3>();
	ArrayList<PointD3> intersectionPointsTriangle2 = new ArrayList<PointD3>();

	System.out.println("Camera:\n" + camera);

	for (int i = 0; i < HEIGHT; i++){
		for (int j = 0; j < WIDTH; j++){

			rays[i][j] = camera.ConstructRayThroughPixel(WIDTH, HEIGHT, j, i, 1, 3 * WIDTH, 3 * HEIGHT);

			ArrayList<PointD3> rayIntersectionPoints   = triangle.  FindIntersections(rays[i][j]);
			ArrayList<PointD3> rayIntersectionPoints2  = triangle2. FindIntersections(rays[i][j]);
			if(rayIntersectionPoints!=null)
				for (PointD3 iPoint: rayIntersectionPoints)
					intersectionPointsTriangle.add(iPoint);
			if(rayIntersectionPoints2!=null)
				for (PointD3 iPoint: rayIntersectionPoints2)
					intersectionPointsTriangle2.add(iPoint);
		}
	}

	assertTrue(intersectionPointsTriangle.size() == 1);
	assertTrue(intersectionPointsTriangle2.size() == 2);

	System.out.println("Intersection Points of Triangles:");
	for (PointD3 iPoint: intersectionPointsTriangle){
		System.out.println(iPoint);
	}
	System.out.println("--");
	for (PointD3 iPoint: intersectionPointsTriangle2){
		System.out.println(iPoint);
	}

	}

}
