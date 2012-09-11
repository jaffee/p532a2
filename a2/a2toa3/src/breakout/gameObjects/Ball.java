package breakout.gameObjects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.HashMap;

import breakout.Drawable;
import breakout.Moveable;

public class Ball implements Moveable, Drawable {
	private double speedX, speedY;
	private Color color;
	private HashMap<Moveable, Integer> collidedWith;
	private static final double DEFAULT_SIZE = 30;
	private static final Color DEFAULT_COLOR = Color.black;
	private Ellipse2D.Double ballShape; // I'd like this to be more generic, but I'm not sure how
	private static final int NO_COLLIDE = 9;
	
	
	public Ball(){
		ballShape = new Ellipse2D.Double(100, 100, Ball.DEFAULT_SIZE, Ball.DEFAULT_SIZE);
		speedX = 1;
		speedY = 1;
		color = Ball.DEFAULT_COLOR;
		collidedWith = new HashMap<Moveable, Integer>();
	}
	
	
	public void move(ArrayList<Moveable> moveables, Dimension boardSize){
		double ballRight = ballShape.getMaxX();
		double ballLeft = ballShape.getMinX();
		double ballTop = ballShape.getMinY();
		double ballBottom = ballShape.getMaxY();
		if(ballRight>boardSize.width){
			if(this.speedX>0)
				this.speedX*=-1;
			else if(this.speedX==0)
				this.speedX=-1;
		}
		else if(ballLeft<0){
			if(this.speedX<0)
				this.speedX*=-1;
			else if(this.speedX==0)
				this.speedX=1;
			
		}
		if(ballTop<0){
			if(this.speedY<0)
				this.speedY*=-1;
			else if(this.speedY==0)
				this.speedY=1;
		}
		else if(ballBottom>boardSize.height){
			if(this.speedY>0)
				this.speedY*=-1;
			else if(this.speedY==0)
				this.speedY=-1;
				
		}
		for(Moveable m : moveables){
			if(m==this){
				continue;
			}
			Integer val;
			try{
				val = collidedWith.get(m);
			}
			catch(NullPointerException e){
				val = null;
			}
			if(val!=null&&val>0){
				collidedWith.put(m, val-1);
				continue;
			}
			else{
				checkCollision(m);
			}
		}
		ballShape.x += speedX;
		ballShape.y += speedY;
	}
	
	private void checkCollision(Moveable m) {
		RectangularShape bounds = m.getBounds();
		RectangularShape ballBounds = this.getBounds();
		if(ballShape.intersects((Rectangle2D) bounds)){
			if(m instanceof Brick){
				((Brick) m).decrementLife();
			}
			boolean changed=false;
			Rectangle2D ballNoX = new Rectangle2D.Double(ballBounds.getX()-speedX, 
													 	 ballBounds.getY(), 
													 	 ballBounds.getWidth(), 
													 	 ballBounds.getHeight());
			if(!ballNoX.intersects((Rectangle2D)bounds)){
				changed=true;
				this.speedX*=-1;
			}
			Rectangle2D ballNoY = new Rectangle2D.Double(ballBounds.getX(), 
				 	 ballBounds.getY()-speedY, 
				 	 ballBounds.getWidth(), 
				 	 ballBounds.getHeight());
			if(!ballNoY.intersects((Rectangle2D)bounds)){
				changed=true;
				this.speedY*=-1;
			}
			if(!changed){
				this.speedX*=-1;
				this.speedY*=-1;
			}
			collidedWith.put(m, new Integer(Ball.NO_COLLIDE));
		}
		
	}
	
	public void draw(Image image){
		Graphics2D g2D = (Graphics2D) image.getGraphics();
		g2D.setColor(color);
		g2D.fill(ballShape);
		//component.repaint();
	}
	
	
	public double getX(){
		return ballShape.x;
	}
	public double getY(){
		return ballShape.y;
	}
	
	
	public double getSpeedX(){
		return speedX;
	}
	public double getSpeedY(){
		return speedY;
	}
	
	
	public void setX(double x){
		ballShape.x = x;
	}
	public void setY(double y){
		ballShape.y = y;
	}
	
	
	public void setSpeedX(double x){
		speedX = x;
	}
	public void setSpeedY(double y){
		speedY = y;
	}
	
	public void setSpeed(double x, double y){
		speedX = x;
		speedY = y;
	}
	
	
	public RectangularShape getBounds(){
		return this.ballShape.getBounds();
	}
	
	public void setColor(Color c){
		this.color = c;
	}

}
