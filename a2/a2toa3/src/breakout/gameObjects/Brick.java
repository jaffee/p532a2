package breakout.gameObjects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

import breakout.Drawable;
import breakout.Moveable;

public class Brick implements Moveable,Drawable,Cloneable {
	
	private double x,y,width,height;
	private static final int DEFAULT_WIDTH = 50;
	private static final int DEFAULT_HEIGHT = 20;
	private static final Color DEFAULT_COLOR = Color.red;
	private Color color;
	private Rectangle2D brickShape;
	private int life;
	private boolean isAlive = true;
	
	public Brick(double x, double y){
		this.x = x;
		this.y = y;
		this.life = 1;
		this.color = Brick.DEFAULT_COLOR;
		this.width = Brick.DEFAULT_WIDTH;
		this.height = Brick.DEFAULT_HEIGHT;
		this.brickShape = new Rectangle((int)this.x, (int)this.y, (int)this.width, (int)this.height);
	}
	
	public Brick(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.brickShape = new Rectangle((int)this.x, (int)this.y, (int)this.width, (int)this.height);
	}

	@Override
	public void draw(Image image) {
		if(isAlive){
			Graphics2D g2D = (Graphics2D) image.getGraphics();
			g2D.setColor(this.color);
			g2D.fill(this.brickShape);
		}
	}

	@Override
	public void move(ArrayList<Moveable> m, Dimension boardSize) {
		if(this.getLife()<=0){
			this.isAlive=false;
		}
//		if(state){
//			for(Moveable moveable:m){
//				Rectangle2D moveableShape = (Rectangle2D)moveable.getBounds();
//				if(this.brickShape.intersects(moveableShape)){
//					this.decrementLife();
//					break;
//				}
//			}
//		}		
	}

	@Override
	public RectangularShape getBounds() {
		if(isAlive){
			return this.brickShape.getBounds();
		}
		else {
			return new Rectangle2D.Double(0, 0, 0, 0);
		}
	}

	public boolean getState() {
		return this.isAlive;
	}
	public void setState(boolean s){
		this.isAlive = s;
	}
	
	
	public double getX(){
		return this.x;
	}
	public double getY(){
		return this.y;
	}
	
	
	public void setX(double x){
		this.x = x;
	}
	public void setY(double y){
		this.y = y;
	}
	
	
	public void setLife(int life){
		this.life = life;
	}
	public int getLife(){
		return this.life;
	}
	
	public void decrementLife(){
		this.life--;
		if(this.life<=0){
			this.isAlive=false;
		}
	}
}
