package test;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Coordinate;
import primitives.PointD3;
import primitives.Vector;

public class PointD3Test {

	@Test
	public void testAdd() {
		PointD3 p1=new PointD3(new Coordinate(1), new Coordinate(3), new Coordinate(5));		
		PointD3 p2=new PointD3(new Coordinate(2), new Coordinate(4), new Coordinate(6));
		Vector v1=new Vector(p2);
		p1.add(v1);
		PointD3 p3=new PointD3(new Coordinate(3), new Coordinate(7), new Coordinate(11));	
		assertEquals(p1,p3);
	
	}
	@Test
	public void testSubTract() {
		PointD3 p1=new PointD3(new Coordinate(1), new Coordinate(5), new Coordinate(10));		
		PointD3 p2=new PointD3(new Coordinate(2), new Coordinate(4), new Coordinate(6));
		Vector v1=new Vector(p2);
		p1.subtract(v1);
		PointD3 p3=new PointD3(new Coordinate(-1), new Coordinate(1), new Coordinate(4));	
		assertEquals(p1,p3);
	
	}
}
