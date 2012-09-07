package edu.indiana.cs.p532;

import java.awt.*;


/**
 * @author minglu
 * @version 1.0
 * @date 30/08/2012
 */

public class Paddle implements Element {

	//set parameters for x/y position ,moving direction and width/height of paddle
	private int posX, posY, width, height;
	private Color color;
	private boolean directUp = false;
	private boolean directDown = false;
		
	//Constructor with x/y/w/h
	public Paddle (int posX, int posY, int width, int height, Color color){
		this.posX=posX;
		this.posY=posY;
		this.width=width;
		this.height=height;
		this.color=color;
	}
	@Override
	public void update(Dimension dim) {
		if (directUp)
			posY-=5;
		if (directDown)
			posY+=5;
		if (posY < 0)
			posY= 0;
		if (posY + this.height> dim.height)
			posY= dim.height- this.height;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(posX, posY, width, height);
	}
	
	public void setDown(boolean valY) {	   	
	     directDown = valY;
	}
	
	public void setUp(boolean valY) {	   	
	     directUp = valY;
	}

	
	public int getPosX(){
		return posX;
	}
	
	public int getPosY(){
		return posY;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public Rectangle getRectangle()
	{
		return new Rectangle(posX, posY, width, height);
	}
}
