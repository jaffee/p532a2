package edu.indiana.cs.p532;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.KeyEvent;

/**
 * This is the class where the actual logic of the game is implemented. The
 * control of the class is given to the key listener and action listener to
 * interact with the game interface.
 * 
 * @param start
 *            object for the class to implement start button
 * @param pause
 *            object for the class to implement pause/restart button
 */

public class ControlClass extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int TIMER_DELAY = Constants.TIMER_DELAY;
	/**
	 * @param args
	 */
	private JButton start;
	private JButton pause;

	private Board board;
	private int brickCount = Constants.BRICK_COUNT;
	private Brick bricks[];
	private Ball ball;
	private Paddle paddle;
	private ScoreBoard scorePanel;
	private TimerHandler daemonThread;
	private ButtonHandler actionHandler;
	private Timer daemon;
	private int score = 0;

	public ControlClass(ScoreBoard scorePanel, Board board, Brick bricks[],
			Ball ball, Paddle paddle) {
		this.board = board;
		this.bricks = bricks;
		this.ball = ball;
		this.paddle = paddle;
		this.scorePanel = scorePanel;

		daemonThread = new TimerHandler();

		actionHandler = new ButtonHandler();

		board.addKeyListener(new PaddleMovementListener());
		board.setFocusable(true);

		daemon = new Timer(TIMER_DELAY, daemonThread);

		start = new JButton("START");
		pause = new JButton("PAUSE/RESUME");

		start.addActionListener(actionHandler);
		pause.addActionListener(actionHandler);

		this.setLayout(new GridLayout(2, 1));
		this.add(start);
		this.add(pause);

	}

	/**
	 * This class is use to listen to the buttons on the applet interface
	 * 
	 * @param brickCount
	 *            this variable contains the number of bricks in the array
	 * @param score
	 *            this variable contains the score of the player
	 */
	class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {

			if (action.getActionCommand().equals("START")) {

				brickCount = Constants.BRICK_COUNT;
				daemon.start();
				score = 0;
				scorePanel.setScore("" + score);

				for (int i = 0; i < bricks.length; i++) {
					bricks[i].setHit(false);
				}
				scorePanel.setScore("0");
				ball.setX(Constants.BALL_INTIAL_XVALUE);
				ball.setVelocityX(Constants.BALL_INTIAL_XVELOCITY);
				ball.setVelocityY(Constants.BALL_INITIAL_YVELOCITY);
				ball.setY(Constants.BALL_INTIAL_YVALUE);
				paddle.setX(Constants.PADDLE_INTIAL_XVALUE);
				paddle.setY(Constants.PADDLE_INTIAL_YVALUE);
				board.requestFocus(true);
				board.draw();

			} else if (action.getActionCommand().equals("PAUSE/RESUME")) {
				if (daemon.isRunning()) {
					daemon.stop();
					board.requestFocus(true);
					board.draw();

				} else{
					board.requestFocus(true);
					board.draw();
					daemon.start();
					
				}

			}

		}
	}

	/**
	 * This class implements serializable object and substitutes the threading
	 * logic. The board is repainted and the collision is checked at every run.
	 */

	class TimerHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			checkCollision();

			ball.setX(ball.getX() + ball.getVelocityX());
			ball.setY(ball.getY() + ball.getVelocityY());
			board.draw();
		}
	}

	/**
	 * This class listens to the keyboard input and takes care of the paddle
	 * movement
	 */
	public class PaddleMovementListener implements KeyListener {

		public void keyPressed(KeyEvent event) {
			switch (event.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if (paddle.getX() > 0) {
					paddle.setX(paddle.getX() - Constants.PADDLE_PRECISION);
				}
				break;

			case KeyEvent.VK_RIGHT:

				if (paddle.getX() + Constants.PADDLE_WIDTH < Constants.BOARD_LENGTH) {
					paddle.setX(paddle.getX() + Constants.PADDLE_PRECISION);
				}

				break;

			}

		}

		public void keyReleased(KeyEvent e) {

		}

		public void keyTyped(KeyEvent e) {

		}

	}

	/**
	 * This function play sounds on different events in the game
	 */
	private void playSound(String fileName) {
		AudioClip clip = Applet.newAudioClip(getClass().getResource(fileName));
		clip.play();
	}

	/**
	 * This function implements the logic to detect the collision between
	 * objects during the play of the game
	 */
	private void checkCollision() {

		if (daemon.isRunning()) {
			// Check the collision between left and right walls

			if (ball.getX() <= Constants.BOARD_OFFSET
					|| (ball.getX() + Constants.BALL_SIZE) > (Constants.BOARD_LENGTH)) {
				ball.setVelocityX(-1 * ball.getVelocityX());
			}

			// Checking for collision with the top wall

			if (ball.getY() <= Constants.BOARD_OFFSET) {
				ball.setVelocityY(-1 * ball.getVelocityY());
			}

			// Game over logic

			if (ball.getY() > Constants.WINDOW_WIDTH) {
				playSound("sound/smb_gameover.wav");
				daemon.stop();
				JOptionPane
				.showMessageDialog(null, "Game Over\nScore:" + score);

			}

			// Check to see if ball touches the paddle

			if (((ball.getX() + Constants.BALL_SIZE) >= paddle.getX())
					&& (ball.getX() <= paddle.getX() + Constants.PADDLE_WIDTH)) {
				if (((ball.getY() + Constants.BALL_SIZE) >= paddle.getY())
						&& ((ball.getY() + Constants.BALL_SIZE) <= (paddle
								.getY() + Constants.PADDLE_WIDTH))) {
					ball.setVelocityY(-1 * ball.getVelocityY());

					playSound("sound/bounce.au");
				}
			}

			// Check for a collision with bricks

			if (brickCount > 0) {
				int count;

				for (count = Constants.BRICK_COUNT - 1; count >= 0; count--) {
					if (!bricks[count].isHit()) {
						if ((ball.getX() <= (bricks[count].getX() + Constants.BRICK_LENGTH))
								&& (ball.getX() + Constants.BALL_SIZE) >= bricks[count]
										.getX()) {

							if ((ball.getY() <= (bricks[count].getY() + Constants.BRICK_WIDTH))
									&& ((ball.getY() + Constants.BALL_SIZE) >= bricks[count]
											.getY())) {

								score = score + 10;
								scorePanel.setScore("" + score);
								playSound("sound/Brick.au");

								// Check for lateral collision with bricks
								if (((ball.getX() + Constants.BALL_SIZE - ball
										.getVelocityX()) <= bricks[count]
												.getX())
												|| ((ball.getX() - ball.getVelocityX()) >= (bricks[count]
														.getX() + Constants.BRICK_LENGTH))) {
									ball.setVelocityX(-1 * ball.getVelocityX());
								} else {
									ball.setVelocityY(-1 * ball.getVelocityY());

								}
								bricks[count].setHit(true);
								brickCount--;
							}
						}
					}
				}
			} else {
				playSound("sound/applause.wav");
				daemon.stop();
				JOptionPane.showMessageDialog(null, "Congratulations\nScore:"
						+ score);

			}

		}
	}
}
