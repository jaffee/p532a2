package breakout_assgn_2_v_1_0;

import java.awt.Image;

import javax.swing.ImageIcon;

/*
 * This class initializes brick array to display all the bricks
 * at the desired location.
 * @param x X co-ordinate of the brick
 * @param y Y co-ordinate of the brick
 * @param hit flag to see check if the brick has bee hit
 */

public class Brick extends GameObjects {

	private static Image brick;
	private boolean hit;

	public Brick(int x, int y) {
		hit = false;
		this.x = x;
		this.y = y;

		img = new ImageIcon(getClass().getResource("brick_new.png"));
		brick = img.getImage();
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	Image gettingImage() {

		return brick;
	}

}
