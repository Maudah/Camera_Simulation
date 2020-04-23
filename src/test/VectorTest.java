package test;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Coordinate;
import primitives.PointD3;
import primitives.Vector;

public class VectorTest {

	@Test
	public void testAdd() {
		PointD3 p1=new PointD3(new Coordinate(1), new Coordinate(3), new Coordinate(5));		
		PointD3 p2=new PointD3(new Coordinate(2), new Coordinate(4), new Coordinate(6));
		Vector v1 =new Vector(p1);
		Vector v2 =new Vector(p2);
		Vector v3=new Vector(new PointD3(new Coordinate(3), new Coordinate(7), new Coordinate(11)));
		v1.add(v2);
		PointD3 p3=new PointD3(v1.getHead());
		PointD3 p4=new PointD3(v3.getHead());	
		assertEquals(p3,p4);

	}
	@Test
	public void testSubtract() {
		PointD3 p1=new PointD3(new Coordinate(1), new Coordinate(4), new Coordinate(9));		
		PointD3 p2=new PointD3(new Coordinate(2), new Coordinate(4), new Coordinate(6));
		Vector v1 =new Vector(p1);
		Vector v2 =new Vector(p2);
		Vector v3=new Vector(new PointD3(new Coordinate(-1), new Coordinate(0), new Coordinate(3)));
		v1.subtract(v2);
		PointD3 p3=new PointD3(v1.getHead());
		PointD3 p4=new PointD3(v3.getHead());
		assertEquals(p3,p4);

	}

	@Test
	public void testScaling() {
		PointD3 p1=new PointD3(new Coordinate(1), new Coordinate(4), new Coordinate(9));		
		Vector v1 =new Vector(p1);
		Vector v3=new Vector(new PointD3(new Coordinate(5), new Coordinate(20), new Coordinate(45)));
		v1.scale(5);
		PointD3 p3=new PointD3(v1.getHead());
		PointD3 p4=new PointD3(v3.getHead());
		assertEquals(p3,p4);

	}
	@Test
	public void testDorProduct () {
		PointD3 p1=new PointD3(new Coordinate(1), new Coordinate(4), new Coordinate(1));		
		PointD3 p2=new PointD3(new Coordinate(2), new Coordinate(1), new Coordinate(6));
		Vector v1 =new Vector(p1);
		Vector v2 =new Vector(p2);
		assertEquals("", 12, v1.dotProduct(v2),1e-10);

	}
	@Test
	public void testLength() {
		PointD3 p1=new PointD3(new Coordinate(1), new Coordinate(2), new Coordinate(2));		
		Vector v1 =new Vector(p1);
		assertEquals("", 3, v1.lenght(),1e-10);
	}

	@Test
	public void testNormalize()
	{
		PointD3 p1=new PointD3(new Coordinate(3.5), new Coordinate(-5), new Coordinate(10));
		PointD3 p2=new PointD3(new Coordinate(0), new Coordinate(), new Coordinate(0));
		Vector v = new Vector(p1);
		v.normalize();
		assertEquals("", 1, v.lenght(),1e-10);
		v = new Vector(p2);
		try 
		{
			v.normalize();
			fail("Didn't throw divide by zero exception!");
		}
		catch (ArithmeticException e) 
		{
			assertTrue(true);
		}
	}
	@Test
	public void testCrossProduct()
	{
		PointD3 p1=new PointD3(new Coordinate(6), new Coordinate(0), new Coordinate(-1));		
		PointD3 p2=new PointD3(new Coordinate(1), new Coordinate(2), new Coordinate(0));
		PointD3 p5=new PointD3(new Coordinate(2), new Coordinate(-1), new Coordinate(12));
		Vector v1 =new Vector(p1);
		Vector v2 =new Vector(p2);
		//System.out.print(v1.crossProduct(v2).getHead());
		assertEquals(p5,v1.crossProduct(v2).getHead());


	}


}
