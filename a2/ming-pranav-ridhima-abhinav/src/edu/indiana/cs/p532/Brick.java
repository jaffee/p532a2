package edu.indiana.cs.p532;

import java.awt.*;

/**
 * @author minglu
 * @version 1.0
 * @date 30/08/2012
 */

public class Brick implements Element {
	//set parameters for x/y position ,moving direction and width/height of brick
	private int posX, posY, width, height;
	private Color color;
	private Boolean interact = false;
	
	//Constructor with x/y/w/h
	public Brick (int posX, int posY, int width, int height, Color color){
		this.posX=posX;
		this.posY=posY;
		this.width=width;
		this.height=height;
		this.color=color;
	}
	
	@Override
	public void update(Dimension dim) {
		interact= true;
	}

	@Override
	public void draw(Graphics g) {
		if (interact == false){
			g.setColor(color);
			g.fillRect(posX, posY, width, height);
		}
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
	
	public Rectangle getRectangle(){	
		return new Rectangle(posX, posY, width, height);	
	}
	
}
