# Camera_simulation
A camera action simulation system for 3D objects.

The "scene" class contains all the scene information such as: the camera location, lightings and shapes in space.  
The “ImageWrite” class actually produces the image  
The “Render” class produces the image according to the data in the scene  
A test file must be created for running.  
For example, the following test:  
```
public void test() {
		Scene scene = new Scene();
		Cylinder sphere = new Cylinder ( new PointD3(0.0, 0.0, -1000),800,new Color(0, 0, 100));
		Material m=new Material();
		m.set_n(20);
		m.set_Ks(1);
		m.set_Kd(1);
		sphere.setMaterial(m);
		scene.AddGeomety(sphere);
		scene.AddLight(new PointLight(new Color(0,100,100), 
      new PointD3(-200,200, -100), 0, 0.00001, 0.000005));
		scene.AddLight(new SpotLight(new Color(255,100,100),
      new PointD3(-200, -200, 100),  0, 0.00001, 0.000005,new Vector(2, 2, -3)));
		
	
		ImageWriter imageWriter = new ImageWriter("Point light test", 500, 500, 500, 500);
		
		Render render = new Render(scene,imageWriter);
		
		render.renderImage();
		render.getRenderImageWrite().writeToimage();
	}
```
Would create the following image:  
![](https://github.com/Maudah/Camera_Simulation/blob/master/Point%20light%20test.jpg)  
More images in this project:

![](https://github.com/Maudah/Camera_Simulation/blob/master/Picture1%20.jpg)  
![](https://github.com/Maudah/Camera_Simulation/blob/master/Emmition%20test.jpg)  
![](https://github.com/Maudah/Camera_Simulation/blob/master/Spot%20test%20plane4%20.jpg)  
![](https://github.com/Maudah/Camera_Simulation/blob/master/Recursive%20Test.jpg)  

