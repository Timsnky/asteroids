package asteroids;

/*
CLASS: Asteroids
DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.
Original code by Dan Leyzberg and Art Simon
*/
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

class Asteroids extends Game implements KeyListener {
	static int counter = 0;
	public Ship ship;
	public Asteroid[] asteroid;
	private int lives = 3;
	private int state = 0;
	private int repeat = 0;
	private int score = 0;
	private static boolean restart = false;
	private static boolean exit = false;
	public ArrayList<Circle> bulletArray = new ArrayList<Circle>();;

  public Asteroids() {	
    super("Asteroids!",800,600);
    this.setFocusable(true);
	this.requestFocus();
	createShip();
	asteroid = createAsteroidArray();
	createAsteroidArray();
	this.addKeyListener(ship);
	this.addKeyListener(this);
  }
  
	public void paint(Graphics brush) {
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);
    	
    	
    	// sample code for printing message for debugging
    	// counter is incremented and this message printed
    	// each time the canvas is repainted
    	counter++;
    	brush.setColor(Color.white);
    	switch(state){
    	case 0: 
    		brush.drawString("Counter is " + counter,10,10);
        	brush.drawString("Lives " + lives,10,30);
        	brush.drawString("Lives " + score,10,50);
    		ship.paint(brush);
        	asteroidPaint(brush);        	
        	ship.move();
        	asteroidMove();
        	moveBullets(brush);
        	checkBulletHit();
        	removeBullets();
        	if(checkCollision()){
        		brush.drawString("COLLISION", 400,300);
        		lives --;
        		state = 1;
        	}
        	if(lives == -1){
        		state = 2;
        	}
        	break;
    	case 1:
    		this.sleepTime = 1000;
    		ship.paint(brush);
        	asteroidPaint(brush);
        	brush.drawString("Counter is " + counter,10,10);
        	brush.drawString("Lives " + lives,10,30);
        	brush.drawString("Lives " + score,10,50);
    		if(repeat == 6){
    			state = 0;
    			this.sleepTime = 10;
    			repeat = 0;
    			asteroid = createAsteroidArray();
    		}
    		if(repeat % 2 == 0){
    			brush.drawString("COLLISION", 400,300);
    		}    		
			repeat ++;
			break;
    	case 2:
    		brush.drawString("GAME OVER !!!", 350,250);
    		brush.drawString("YOUR SCORE = " + score, 350,300);
    		brush.drawString("NEW GAME?   Y  /  N", 350,350);
    		break;
    	}
  }
	
	@Override
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub
		int keyCode = key.getKeyCode();
		
		if(keyCode == KeyEvent.VK_SPACE){
			Point start = new Point(ship.point[0].x , ship.point[0].y);
			double angle = ship.rotationAngle;
			Circle bullet = new Circle(start, angle);
			bulletArray.add(bullet);
		}
		else if(keyCode == KeyEvent.VK_Y){
			restart = true;
		}
		else if(keyCode == KeyEvent.VK_N){
			exit = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void moveBullets(Graphics brush){
		int index = 0;
		while(index < bulletArray.size()){			
			bulletArray.get(index).move();
			bulletArray.get(index).paint(brush);
			index ++;
		}		
	}
	
	public void removeBullets(){
		int index = 0;
		while(index < bulletArray.size()){
			if(bulletArray.get(index).center.x > 800 || bulletArray.get(index).center.x < 0
					|| bulletArray.get(index).center.y > 600 || bulletArray.get(index).center.y < 0){
				bulletArray.remove(index);
			}
			index ++;
		}		
	}
	
	public void createShip(){
		Point p = new Point(400,270);
		Point p2 = new Point(390,320);
		Point p3 = new Point(410,320);
		Point[] shape = {p,p2,p3};
		Point center = new Point(400,300);
		ship = new Ship(shape, center,0);
	}
	
	public boolean checkCollision(){
		int index = 0;
		while(index < 6){
			if(ship.checkIntersection(asteroid[index]) || asteroid[index].checkIntersection(ship)){
				return true;
			}
			index ++;
		}
		return false;
	}
	
	public void checkBulletHit(){
		int index = 0;
		while(index < 6){
			int index1 = 0;
			while(index1 < bulletArray.size()){
				if(asteroid[index].contains(bulletArray.get(index1).center)){
					asteroid[index] = createAsteroid();
					bulletArray.remove(index1);
					score += 10;
				}
				index1 ++;
			}
			index ++;
		}
	}
	
	public Asteroid createAsteroid(){
		Random random = new Random();
		int category, x = 0 , y = 0 , angle = 0, type = 0;
		
		category = random.nextInt(4);
		switch(category){
		case 0:
			x = -65;
			y = random.nextInt(601);
			angle = random.nextInt(91) + 45;
			break;
		case 1:
			x = random.nextInt(801);
			y = -95;
			angle = random.nextInt(91) + 135;
			break;
		case 2:
			x = 865;
			y = random.nextInt(601);
			angle = random.nextInt(91) + 225;
			break;
		case 3:
			x = random.nextInt(801);
			y = 695;
			angle = random.nextInt(91) + 315;
			break;
		}
		type = random.nextInt(2);
		Point p = null,p1 = null,p2 = null,p3 = null,p4 = null,p5 = null,p6 = null,p7 = null,p8 = null,p9 = null,p10 = null,p11 = null,center = null;
		switch(type){
		case 0:
			p = new Point(x , y);
			p1= new Point(x - 30, y);
			p2 = new Point(x - 40,y + 25);
			p3 = new Point(x - 70 , y + 20);
			p4 = new Point(x - 70, y + 70);
			p5 = new Point(x - 35, y + 85);
			p6 = new Point(x - 35, y + 100);
			p7 = new Point(x + 5, y + 100);
			p8 = new Point(x - 10, y + 65);
			p9 = new Point(x + 30, y + 80);
			p10 = new Point(x + 30, y + 25);
			p11 = new Point(x + 15, y + 25);
			Point[] shape = {p,p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11};
			center = new Point(x - 20, y + 50);
			break;
		case 1:
			p = new Point(x , y);
			p1= new Point(x - 30, y);
			p2 = new Point(x - 35,y + 25);
			p3 = new Point(x - 65 , y + 35);
			p4 = new Point(x - 65, y + 75);
			p5 = new Point(x - 40, y + 60);
			p6 = new Point(x - 50, y + 100);
			p7 = new Point(x + 10, y + 100);
			p8 = new Point(x - 5, y + 65);
			p9 = new Point(x + 35, y + 85);
			p10 = new Point(x + 35, y + 40);
			p11 = new Point(x + 25, y + 15);			
			center = new Point(x - 15, y + 50);
			break;			
		}
		Point[] shape = {p,p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11};
		return new Asteroid(shape, center, angle);
		
	}
	
	public Asteroid[] createAsteroidArray(){
		Asteroid[] asteroidArray = new Asteroid[6];
		
		int index = 0;
		
		while(index < 6){
			asteroidArray[index] = createAsteroid();
			index ++;
		}		
		return asteroidArray;
	}
	
	public void asteroidPaint(Graphics brush){
		int index = 0;
		while (index < 6){
			asteroid[index].paint(brush);
			index ++;
		}
	}
	
	public void asteroidMove(){
		int index = 0;
		int newAsteroid;
		while (index < 6){
			newAsteroid = asteroid[index].move();
			if(newAsteroid == 1){
				asteroid[index] = createAsteroid();
			}
			index ++;
		}
	}
  
	public static void main (String[] args) {
   		Asteroids a = new Asteroids();
   		while(!exit){
   			if(restart){
   				a.frame.dispose();
   				a = new Asteroids();
   				restart = false;
   			}
   			a.repaint(); 
   		}
   		a.frame.dispose();
  }

	

	
}