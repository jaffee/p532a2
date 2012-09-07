package edu.indiana.cs.p532;

import java.awt.*;

/**
 * @author minglu
 * @version 1.0
 * @date 30/08/2012
 */

public class Ball implements Element {

	//set parameters for x/y position ,moving speed and radius of ball
	private int posX, posY, speedX, speedY, radius;
	private Color color;
	
	//Constructor with x/y/radius/speed/color
	public Ball (int posX, int posY, int speed, int radius, Color color){
		this.posX=posX;
		this.posY=posY;
		this.speedX=speed;
		this.speedY=speed;
		this.radius=radius;
		this.color=color;
	}
		
	@Override
	public void update(Dimension dim) {
		posX += speedX;
		posY += speedY;
		
		if (posX < 0)
			speedX = Math.abs(speedX);
		if (posX > dim.width)
			speedX = -Math.abs(speedX);
		if (posY < 0)
			speedY = Math.abs(speedY);
		if (posY > dim.height)
			speedY = -Math.abs(speedY);	
	}

	public void checkBounce(Paddle paddle) {
		if(getBoundingBox().intersects(paddle.getRectangle())) {			
			
			//create a  bounding rectangle for each side 
		    //first for the bounding rectangle 
			    Rectangle rect1 = new Rectangle( paddle.getPosX(),paddle.getPosY() - 3,paddle.getWidth(), 6 );
			    Rectangle rect2 = new Rectangle(paddle.getPosX(), paddle.getPosY()+paddle.getHeight() - 2 ,paddle.getWidth(),7 );
			    Rectangle rect3 = new Rectangle(paddle.getPosX()-3, paddle.getPosY() ,6, paddle.getHeight() );
			    Rectangle rect4 = new Rectangle(paddle.getPosX()+ paddle.getWidth() - 3, paddle.getPosY() ,6, paddle.getHeight() );
			
			    if(getBoundingBox().intersects(rect1) && speedY > 0){			    
				    speedY = -speedY;
			    }
			    else if(getBoundingBox().intersects(rect4) && speedX < 0){			
			        speedX = Math.abs(speedX);
			    }			
			    else if(getBoundingBox().intersects(rect2) && speedY < 0){
				    speedY = -speedY;				  
			    }
			    else if (getBoundingBox().intersects(rect3) && speedX > 0){			
				    speedX = -Math.abs(speedX);
			    }	
		}
	}
	
	public Boolean checkBrick(Brick brick) {
		if(getBoundingBox().intersects(brick.getRectangle())) 
		{
			return true;	
		}				
		return false;
	}
	
	public Rectangle getBoundingBox()
	{
	    return new Rectangle(posX - radius, posY + radius, radius * 2, radius * 2);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(posX-radius, posY-radius, radius*2, radius*2); //x, y position are the center of the ball
	}
	
	public int getX(){
		return posX;
	}
	
	public int getY(){
		return posY;
	}

}
