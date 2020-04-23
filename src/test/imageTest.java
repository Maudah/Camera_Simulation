package test;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Renderer.ImageWriter;

class imageTest {

	@Test
	void test() {
		ImageWriter imm=new ImageWriter("firstPicture", 500, 500,500, 500);
		for(int i=1;i<500;i++)
		{
			for(int j=1;j<500;j++)
			{
				if(i%50==0||j%50==0)
					imm.writePixel(i, j, 255,255,255);
				
			}
		}
		imm.writeToimage();


	}

}
