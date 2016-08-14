package asteroids;

import java.awt.Graphics;

public class Asteroid extends Polygon {
	public Point[] point;
	public Point offset;
	public double rotationAngle;

	public Asteroid(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
		// TODO Auto-generated constructor stub
		this.point = this.getPoints();
		this.offset = inPosition;
		this.rotationAngle = inRotation;
	}
	
	public void paint(Graphics brush){	
		int index = 0;
		int xPoints[] = new int[12];
		int yPoints[] = new int[12];
		while( index < 12){
			xPoints[index] = (int) point[index].x;
			yPoints[index] = (int) point[index].y;
			index++;
		}
		
		brush.drawPolygon(xPoints,yPoints,12);
	}
	
	public int move(){
		int index = 0;
		while(index < 12){
			point[index].x = point[index].x + Math.sin(Math.toRadians(rotationAngle));
			point[index].y = point[index].y - Math.cos(Math.toRadians(rotationAngle));
			index ++;
		}
		offset.x = offset.x + Math.sin(Math.toRadians(rotationAngle));
		offset.y = offset.y - Math.cos(Math.toRadians(rotationAngle));
		if(offset.y < -50 || offset.y > 650 || offset.x > 850 || offset.x < -50){
			return 1;
		}
		else{
			return 0;
		}
		
	}
	

}
