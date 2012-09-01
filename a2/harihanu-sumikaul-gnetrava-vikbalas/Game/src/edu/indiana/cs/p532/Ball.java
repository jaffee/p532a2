package edu.indiana.cs.p532;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * This class initiates Ball object and all its related attributes
 * 
 * @param x X co-ordinate of the ball
 * @param y Y co-ordinate of the ball
 * @param vx X velocity of the ball
 * @param vy Y velocity of the ball
 */

public class Ball extends GameObjects {

	private static Image ball;
	private int vx, vy;

	public Ball(int x, int y) {

		this.x = x;
		this.y = y;
		vx = 0;
		vy = 0;

		img = new ImageIcon(getClass().getResource("images/ball.png"));
		ball = img.getImage();
	}

	public void setVelocityX(int vx) {
		this.vx = vx;

	}

	public void setVelocityY(int vy) {
		this.vy = vy;
	}

	/*
	 * @return vx X velocity of the ball
	 */

	public int getVelocityX() {
		return vx;
	}

	/*
	 * @return vy Y velocity of the ball
	 */

	public int getVelocityY() {
		return vy;
	}

	/*
	 * @return ball image of the ball
	 */

	Image gettingImage() {

		return ball;
	}

}
