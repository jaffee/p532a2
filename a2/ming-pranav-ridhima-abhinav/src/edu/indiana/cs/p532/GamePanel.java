package edu.indiana.cs.p532;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * @author minglu
 * @version 1.1
 * @date 06/09/2012
 */
public class GamePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Image image;
	private Graphics graphic;
	private Ball ball;
	private Paddle paddle;
	private Brick brick;
	
	public GamePanel(Ball ball, Paddle paddle, Brick brick) {
		this.ball=ball;
		this.paddle =paddle;
		this.brick=brick;
    	this.setSize(600,600);
    }
	
	public void paint(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getSize().width, this.getSize().height);
		ball.draw(g);
		paddle.draw(g);
		brick.draw(g);
	}
	
	public void update(Graphics g){
		 if (image == null){
			 image = createImage(this.getSize().width, this.getSize().height);
			 graphic = image.getGraphics();
		 }
		 
		 graphic.setColor(this.getBackground());
		 graphic.fillRect(0, 0, this.getSize().width, this.getSize().height);
		 graphic.setColor(this.getForeground());
		 
		 paint(graphic);
		 g.drawImage(image, 0, 0, this);
	}
	
}
