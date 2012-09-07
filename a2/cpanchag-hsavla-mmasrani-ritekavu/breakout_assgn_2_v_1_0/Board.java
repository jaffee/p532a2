package breakout_assgn_2_v_1_0;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/*
 * This class initializes the main board interface of
 * the game. All the constructors are called in. The repaint
 * function the does the job of repainting the board
 * to display the updated contents.
 * @param bricks Array of Brick class
 * @param ball Object of the Ball
 * @param paddle Object of the paddle
 */

public class Board extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Brick[] bricks;
	private Ball ball;
	private Paddle paddle;
	private int i;

	public Board(Brick[] bricks, Ball ball, Paddle paddle) {
		this.setSize(new Dimension(Constants.BOARD_LENGTH,
				Constants.BOARD_WIDTH));

		this.bricks = bricks;
		this.ball = ball;
		this.paddle = paddle;

		this.setBackground(Color.WHITE);

		this.setFocusable(true);
		this.requestFocusInWindow();

	}

	public void paint(Graphics g) {
		super.paint(g);
		for (i = 0; i < Constants.BRICK_COUNT; i++) {
			if (!bricks[i].isHit()) {
				g.drawImage(bricks[i].gettingImage(), (int) bricks[i].getX(),
						(int) bricks[i].getY(), null);
			}
		}

		g.drawImage(paddle.gettingImage(), (int) paddle.getX(), (int) paddle.getY(), null);

		g.drawImage(ball.gettingImage(),(int) ball.getX(), (int) ball.getY(), null);

	}

	public void draw() {
		repaint();
	}
}
