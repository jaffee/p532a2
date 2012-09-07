package breakout_assgn_2_v_1_0;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * This class initializes the paddle
 * 
 * @param x
 *            X co-ordinates of the paddle
 * @param y
 *            Y co-ordinates of the paddle
 */

public class Paddle extends GameObjects {

	

	public Paddle(int x, int y) {

		this.x = x;
		this.y = y;
		img = new ImageIcon(getClass().getResource("paddle.png"));
		paddle = img.getImage();
	}

	Image gettingImage() {

		return paddle;
	}
}
