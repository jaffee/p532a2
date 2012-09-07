package breakout_assgn_2_v_1_0;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * All the classes extend the methods from this class so that the code is reused
 * wherever applicable.
 */

public class GameObjects {

	protected static Image ball;
	protected static Image paddle;
	ImageIcon img;
	protected double x, y;

	double getX() {

		return x;
	}

	double getY() {

		return y;
	}

	public void setX(double d) {
		this.x = d;
	}

	public void setY(double y) {
		this.y = y;
	}
}
