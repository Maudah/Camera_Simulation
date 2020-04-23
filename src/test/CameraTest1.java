package test;

import static org.junit.Assert.*;


import org.junit.Test;
import primitives.Coordinate;
import primitives.PointD3;
import primitives.Vector;

//import static org.junit.jupiter.api.Assertions.*;

import elements.camera;

import primitives.Ray;

public class CameraTest1 {

	@Test
	public void testRayConstruction() {

		final int WIDTH  = 3;
		final int HEIGHT = 3;
		PointD3 p = new PointD3 (new Coordinate(0.0), new Coordinate(0.0), new Coordinate(0.0));
		PointD3 p1 = new PointD3(new Coordinate(0.0), new Coordinate(1.0), new Coordinate(0.0));
		PointD3 p2 = new PointD3(new Coordinate(1.0), new Coordinate(0.0), new Coordinate(0.0));


		Vector v1 = new Vector(p1);
		Vector v2 = new Vector(p2);

		PointD3[][] screen = new PointD3 [HEIGHT][WIDTH];

		camera camera = new camera(p, v1, v2);

		System.out.println("Camera:\n" + camera);
		System.out.println("##");

		for (int i = 0; i < HEIGHT; i++){
			for (int j = 0; j < WIDTH; j++){

				Ray ray = camera.ConstructRayThroughPixel(WIDTH, HEIGHT, i, j, 1,WIDTH*3, HEIGHT*3);
				//	WIDTH, HEIGHT, j, i, 1, 3 * WIDTH, 3 * HEIGHT);

				screen[i][j] = ray.getPoo();

				System.out.print(" ^^ " + screen[i][j]);
				System.out.println(" *** " + ray.getDirection());

				// Checking z-coordinate
				//	assertTrue(Double.compare(screen[i][j].getZ().getCoordinate(), -1.0) == 0);
				assertEquals("",screen[i][j].getZ().getX(),-1,0.1);
				// Checking all options
				double x = screen[i][j].getX().getX();
				double y = screen[i][j].getX().getX();

				if (Double.compare(x, 3) == 0 || 
						Double.compare(x, 0) == 0 ||
						Double.compare(x, -3) == 0){
					if (Double.compare(y, 3) == 0 || 
							Double.compare(y, 0) == 0 ||
							Double.compare(y, -3) == 0){
						assertTrue(true);
					} else {
						fail("Wrong y coordinate");
					}
				} 
				else 
				{
					fail("Wrong x coordinate");
				}

			}
			System.out.println("--");
		}



	}

}
