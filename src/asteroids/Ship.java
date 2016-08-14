package asteroids;

import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Ship extends Polygon implements KeyListener{
	public Point[] point;
	public Point offset;
	public double rotationAngle;
	public boolean forwardKey = false;
	public boolean rightKey = false;
	public boolean leftKey = false;

	public Ship(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
		// TODO Auto-generated constructor stub
		point = this.getPoints();
		offset = inPosition;
		rotationAngle = inRotation;		
	}
	
	public void paint(Graphics brush){
		int xPoints[] = new int[3];
		int yPoints[] = new int[3];
		int index = 0;
		while( index < 3){
			xPoints[index] = (int)point[index].x;
			yPoints[index] = (int)point[index].y;
			index++;
		}
		brush.drawPolygon(xPoints,yPoints,3);
	}
	
	public void move(){		
		if(forwardKey){
			int index = 0, index1 = 0;
			while( index1 < 3){
				point[index1].x = point[index1].x + 2 * Math.sin(Math.toRadians(rotationAngle));
				point[index1].y = point[index1].y - 2 * Math.cos(Math.toRadians(rotationAngle));
				index1++;
			}
			offset.x = offset.x + 2 * Math.sin(Math.toRadians(rotationAngle));
			offset.y = offset.y - 2 * Math.cos(Math.toRadians(rotationAngle));
		
			if( point[1].y > 600 && point[2].y > 600){
				while( index < 3){
					point[index].y = point[index].y - 600;
					index++;
				}
				offset.y = 600 - offset.y;
			}else if(point[1].y < 0 && point[2].y < 0 ){
				while( index < 3){
					
					point[index].y = 600 + point[index].y;
					index++;
				}
				offset.y = 600 + offset.y;
			}else if(point[1].x > 800 && point[2].x > 800){
				while( index < 3){
					point[index].x =  point[index].x - 800;
					index++;
				}
				offset.x = 800 - offset.x;
			}else if(point[1].x < 0 && point[2].x < 0){
				while( index < 3){
					point[index].x = 800 + point[index].x;
					index++;
				}
				offset.x = 800 + offset.x;
			}
			
		}
		if(rightKey){
			this.rotate(2);			
			point = this.getPoints();
			this.rotationAngle = rotation;
		}
		if(leftKey){
			this.rotate(-2);
			point = this.getPoints();
			this.rotationAngle = rotation;
		}
	}

	@Override
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub
		int keyCode = key.getKeyCode();
		
		switch(keyCode){
			case KeyEvent.VK_UP:
				forwardKey = true;
				break;
			case KeyEvent.VK_RIGHT:
				rightKey = true;
				break;
			case KeyEvent.VK_LEFT:
				leftKey = true;
				break;
		}		
	}

	@Override
	public void keyReleased(KeyEvent key) {
		// TODO Auto-generated method stub
		int keyCode = key.getKeyCode();
				
				switch(keyCode){
					case KeyEvent.VK_UP:
						forwardKey = false;
						break;	
					case KeyEvent.VK_RIGHT:
						rightKey = false;
						break;
					case KeyEvent.VK_LEFT:
						leftKey = false;
						break;
				}		
	}

	@Override
	public void keyTyped(KeyEvent key) {
		// TODO Auto-generated method stub		
	}

}
