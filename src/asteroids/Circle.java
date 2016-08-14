package asteroids;

import java.awt.Graphics;

public class Circle {
	
	public Point center;   // The offset mentioned above.
	public double angle;
	
	public Circle(Point center, double angle){
		this.center = center;
		this.angle = angle;	
	}
	
	public void paint(Graphics brush){
		brush.fillOval((int)center.x, (int)center.y, 3 , 3);
	}
	
	public void move(){
		center.x = center.x + 5 * Math.sin(Math.toRadians(angle));
		center.y = center.y - 5 * Math.cos(Math.toRadians(angle));
	}
}
