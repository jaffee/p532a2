package edu.indiana.cs.p532;

import java.awt.BorderLayout;

import javax.swing.JApplet;
import javax.swing.JPanel;

/**
 * This class is used to initialize the Applet. It calls all the other objects
 * of the class to initialize their constructors.
 * 
 * @param board Object of class Board
 * @param control object of the ControlClass
 * @param scorePanel object of thE score Panel
 * @param bricks array of Brick objects
 * @param ball object of the Ball class
 * @param paddle object of the Paddle class
 */

public class BrickBreaker extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Board board;
	private ControlClass control;
	private ScoreBoard scorePanel;

	private Brick[] bricks;
	private Ball ball;
	private Paddle paddle;

	private int i, x, j;

	public void init() {

		bricks = new Brick[Constants.BRICK_COUNT];
		for (i = 0; i < Constants.BRICK_COUNT; i++) {
			if (x > 4) {
				x = 0;
				j++;
			}
			bricks[i] = new Brick(20 + (x * 65), 20 + (j * 25));
			x++;
		}

		ball = new Ball(Constants.BALL_INTIAL_XVALUE,
				Constants.BALL_INTIAL_YVALUE);

		paddle = new Paddle(Constants.PADDLE_INTIAL_XVALUE,
				Constants.PADDLE_INTIAL_YVALUE);
		board = new Board(bricks, ball, paddle);
		scorePanel = new ScoreBoard();

		control = new ControlClass(scorePanel, board, bricks, ball, paddle);
		setSize(Constants.WINDOW_LENGTH, Constants.WINDOW_WIDTH);

		JPanel infoPanel = new JPanel(new BorderLayout());
		infoPanel.add(control, BorderLayout.SOUTH);
		infoPanel.add(scorePanel, BorderLayout.NORTH);

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(infoPanel, BorderLayout.EAST);
		mainPanel.add(board, BorderLayout.CENTER);
		setContentPane(mainPanel);
		mainPanel.setFocusable(true);
		mainPanel.requestFocusInWindow();
	}

}
