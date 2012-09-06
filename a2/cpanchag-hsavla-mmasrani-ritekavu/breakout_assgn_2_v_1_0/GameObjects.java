package breakout_assgn_2_v_1_0;

import javax.swing.ImageIcon;

/**
 * All the classes extend the methods from this class so that the code is reused
 * wherever applicable.
 */

public class GameObjects {

	ImageIcon img;
	protected int x, y;

	int getX() {

		return x;
	}

	int getY() {

		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
