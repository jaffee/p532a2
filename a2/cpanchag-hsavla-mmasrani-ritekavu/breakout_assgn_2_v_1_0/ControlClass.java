package breakout_assgn_2_v_1_0;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/** This is the class where the actual logic of the game is implemented. The
 * control of the class is given to the key listener and action listener to
 * interact with the game interface.
 *
 * This class acts as the client in the Command pattern.
 * 
 * @param start
 *            object for the class to implement start button
 * @param pause
 *            object for the class to implement pause/restart button */

public class ControlClass extends JPanel {
	private static final long serialVersionUID = 1L;
	private static double timerStates;
	boolean pauseMode=false;
	private static int timerTicksReplay = 0;
	private static final int TIMER_DELAY = Constants.TIMER_DELAY;
	private static int count = 0;
	private JButton start;
	private JButton pause;
	private JButton undoButton;
	private JButton replayButton;
	private int clockTick;
	private double clockTime;
	private String clockTimeString;
	private Board board;
	private int brickCount = Constants.BRICK_COUNT;
	private Brick bricks[];
	private Ball ball;
	private Paddle paddle;
	private ScoreBoard scorePanel;
	private TimerHandler daemonThread;	// handles timer events
	private ButtonHandler actionHandler;	// handles button events
	private Timer daemon;			
	private int score = 0;	// this variable is set to proper command by the function setCommand
	private Command command;
	private Command paddleRightMoveCommand;
	private Command paddleLeftMoveCommand;
	private ArrayList<SavedObjectsStates> savedObjectStates; // this arraylist stores the SavedObjectsStates which contains the state for ball, paddle and brick
	private boolean undoButtonPressedFirstTime;	// is true when undo is pressed first time, then becomes false until start or replay is pressed
	private int whichPrevBallUndoObject;	
	private boolean isReplayChecked;	// this checks whether the reply button has been checked
	private CommandClass commandClass;	// used to set and execute command

	public ControlClass(ScoreBoard scorePanel, Board board, Brick bricks[], Ball ball, Paddle paddle) {
		this.board = board;
		this.bricks = bricks;
		this.ball = ball;
		this.paddle = paddle;
		this.scorePanel = scorePanel;
		daemonThread = new TimerHandler();
		actionHandler = new ButtonHandler();

		board.addKeyListener(new PaddleMovementListener());
		board.addKeyListener(new ButtonHandler());

		board.setFocusable(true);
		daemon = new Timer(TIMER_DELAY, daemonThread);
		start = new JButton("START");
		pause = new JButton("PAUSE/RESUME");
		undoButton = new JButton("UNDO");
		replayButton = new JButton("REPLAY");
		start.addActionListener(actionHandler);
		pause.addActionListener(actionHandler);
		undoButton.addActionListener(actionHandler);
		replayButton.addActionListener(actionHandler);
		isReplayChecked = false;					
		savedObjectStates = new ArrayList<SavedObjectsStates>();
		undoButtonPressedFirstTime = true;
		paddleLeftMoveCommand = new PaddleLeftMoveCommand(paddle);	// set the paddle with the paddleLeftMoveCommand
		paddleRightMoveCommand = new PaddleRightMoveCommand(paddle);	// set the paddle with the paddleRightMoveCommand
		commandClass = new CommandClass();				// invoker of the commands
		this.setLayout(new GridLayout(4, 1));
		this.add(start);
		this.add(pause);
		this.add(undoButton);
		this.add(replayButton);
	}

	// executed when replay button is clicked
	public void replay() {
		score=0;
		scorePanel.setScore(""+score);
		count = 0;
		isReplayChecked = true;
		daemon.start();
	}
	/** This class is use to listen to the buttons on the applet interface
	 * 
	 * @param brickCount
	 *            this variable contains the number of bricks in the array
	 * @param score
	 *            this variable contains the score of the player */
	class ButtonHandler implements ActionListener, KeyListener {

		public void actionPerformed(ActionEvent action) {

			// start buton pressed
			if (action.getActionCommand().equals("START")) {
				pauseMode=false;
				brickCount = Constants.BRICK_COUNT;
				clockTick = 0;
				if (!savedObjectStates.isEmpty())
					savedObjectStates.removeAll(savedObjectStates);
				daemon.start();
				score = 0;
				scorePanel.setScore("" + score);
				for (int i = 0; i < bricks.length; i++) {
					bricks[i].setHit(false);
				}
				scorePanel.setScore("0");

				// sets the ball in random direction
				Random r=new Random();
				double s1=0,s2=0;
				int d1; 
				d1=r.nextInt(2);
				
				//System.out.println("D: X Y"+d1+" "+d2);
				double randomStartX;
				s1=r.nextDouble();
				s2=r.nextDouble();
				if(d1==0){
					randomStartX=1+s1*4;
				}
				else {
					randomStartX=-1-s1*4;
				}
					
				double randomStartY=1+s2*4;
				
				// initialize the ball and paddle
				ball.setX(Constants.BALL_INTIAL_XVALUE);
				ball.setVelocityX(randomStartX);
				ball.setVelocityY(randomStartY);
				ball.setY(Constants.BALL_INTIAL_YVALUE);
				paddle.setX(Constants.PADDLE_INTIAL_XVALUE);
				paddle.setY(Constants.PADDLE_INTIAL_YVALUE);
				board.requestFocus(true);
				commandClass.setCommand((new BallMoveCommand(ball)));	// register ballMove command
				commandClass.buttonWasPressed();			// set the ball moving
				board.draw();		
			} 
			// undo button presses
			else if (action.getActionCommand().equals("UNDO") && pauseMode) {

				if (undoButtonPressedFirstTime) {
					if (savedObjectStates.size() != 0) {
						updateTimer();
						whichPrevBallUndoObject = savedObjectStates.size() - 1;	// retrieve last object
						brickPresentChecker(whichPrevBallUndoObject);
						savedObjectStates.get(whichPrevBallUndoObject).getBallMoveCommand().undo();
						savedObjectStates.get(whichPrevBallUndoObject).getPaddleMoveCommand().undo();
						undoButtonPressedFirstTime = false;	
						savedObjectStates.remove(whichPrevBallUndoObject);	
						whichPrevBallUndoObject -= 1;	// keeps track of which last ball object was retrieved
					}
				} else {

					if (savedObjectStates.size() != 0) {
						updateTimer();
						brickPresentChecker(whichPrevBallUndoObject);
						savedObjectStates.get(whichPrevBallUndoObject).getBallMoveCommand().undo();
						savedObjectStates.get(whichPrevBallUndoObject).getPaddleMoveCommand().undo();
						savedObjectStates.remove(whichPrevBallUndoObject);
						whichPrevBallUndoObject -= 1;
					}
				}

				board.draw();
			}

			// replay button pressed
			else if (action.getActionCommand().equals("REPLAY")) {
				if (!daemon.isRunning())
					replay();
			}

			// pause button pressed
			else if (action.getActionCommand().equals("PAUSE/RESUME")) {

				if (daemon.isRunning()) {
					daemon.stop();
					board.requestFocus(false);
					undoButtonPressedFirstTime = true;
					board.draw();
					

				}
				else
					daemon.start();
				board.requestFocus(true);
				pauseMode=!pauseMode;
				
			}
		}

		// This function checks which of the bricks have been blown up and 
		public void brickPresentChecker(int whichPrevBallUndoObject) {

			boolean[] brickArray = savedObjectStates.get(whichPrevBallUndoObject).getIsBrickHit();

			for (int i = 0; i < Constants.BRICK_COUNT; i++) {

				if (brickArray[i] == true) {
					if(bricks[i].isHit()!=true)
					{
						bricks[i].setHit(true);}
				}
				else if (brickArray[i] == false)
				{
					if(bricks[i].isHit()!=false){
						bricks[i].setHit(false);
						score=score-10;
						scorePanel.setScore(""+score);
					}
				}
			}
		}

		// used for updating timer when undo button is pressed
		public void updateTimer() {

			timerStates = savedObjectStates.size();
			timerStates = timerStates / 100;
			String clockTimeString = new Double(timerStates).toString();
			scorePanel.setClock(clockTimeString);
			board.draw();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyChar()=='u' && pauseMode){
					
				if (undoButtonPressedFirstTime) {
					if (savedObjectStates.size() != 0) {
						updateTimer();
						whichPrevBallUndoObject = savedObjectStates.size() - 1;
						brickPresentChecker(whichPrevBallUndoObject);
						savedObjectStates.get(whichPrevBallUndoObject).getBallMoveCommand().undo();
						savedObjectStates.get(whichPrevBallUndoObject).getPaddleMoveCommand().undo();
						undoButtonPressedFirstTime = false;
						savedObjectStates.remove(whichPrevBallUndoObject);
						whichPrevBallUndoObject -= 1;
					}
				} else {

					if (savedObjectStates.size() != 0) {
						updateTimer();
						brickPresentChecker(whichPrevBallUndoObject);
						savedObjectStates.get(whichPrevBallUndoObject).getBallMoveCommand().undo();
						savedObjectStates.get(whichPrevBallUndoObject).getPaddleMoveCommand().undo();
						savedObjectStates.remove(whichPrevBallUndoObject);
						whichPrevBallUndoObject -= 1;
					}
				}

				board.draw();

				
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

	/** This class implements serializable object and substitutes the threading
	 * logic. The board is repainted and the collision is checked at every run. */

	class TimerHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			// check if the replay button is checked
			if (!isReplayChecked) {

				clockTick++;
				clockTime = (double) (clockTick / 100.0);
				clockTimeString = new Double(clockTime).toString();
				scorePanel.setClock(clockTimeString);
				checkCollision();

				Command c = new BallMoveCommand(ball);		// create new ballMoveCommand instance
				((BallMoveCommand) c).execute();

				Command c1 = new PaddleMoveCommand(paddle);
				((PaddleMoveCommand) c1).execute();

				boolean[] brickBooleanArray = new boolean[Constants.BRICK_COUNT];	// stores the state of the bricks

				for (int i = 0; i < Constants.BRICK_COUNT; i++) {
					brickBooleanArray[i] = bricks[i].isHit();
				}

				// create new instance with ballMoveCommand, paddleMoveCommand and brickBoolean array
				SavedObjectsStates s = new SavedObjectsStates(c, c1, brickBooleanArray);	
				savedObjectStates.add(s);		// add newly created instance to arraylist
				board.draw();				// update the screen
			}

			else {
				if (count < savedObjectStates.size()) {

					SavedObjectsStates s = savedObjectStates.get(count);
					Command c = s.getBallMoveCommand();	// retrieve previous ballMoveCommand
					PaddleMoveCommand cPaddle=(PaddleMoveCommand) s.getPaddleMoveCommand();	// retrieve previous paddleMoveCommand
					boolean[] brickArray = savedObjectStates.get(count).getIsBrickHit();	// retrieve previous brick array

					for (int i = 0; i < Constants.BRICK_COUNT; i++) {

						if (brickArray[i] == true) {

							if(bricks[i].isHit()==false){
								bricks[i].setHit(true);
								score=score+10;
								scorePanel.setScore(""+score);
							}
						} else if (brickArray[i] == false) {
							bricks[i].setHit(false);
						}
					}

					ball.setX(((BallMoveCommand) c).getPrevX());	// set the ball x and y co-ordinates to previous values
					ball.setY(((BallMoveCommand) c).getPrevY());
					paddle.setX(cPaddle.getPrevX());		// set the paddle's x co-ordinate to previous value
					count++;
					// update the timer string
					timerTicksReplay++;
					double timerTickTemp = timerTicksReplay / 100.0;
					scorePanel.setClock(((Double) timerTickTemp).toString());
					board.draw();
				} else {
					daemon.stop();
					isReplayChecked = false;
					timerTicksReplay = 0;
				}

			}
		}

	}

	/** This class listens to the keyboard input and takes care of the paddle
	 * movement */
	public class PaddleMovementListener implements KeyListener {

		public void keyPressed(KeyEvent event) {

			switch (event.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if (paddle.getX() > 0) {
						commandClass.setCommand((paddleLeftMoveCommand));	// set the command to paddleLeftMove when left key is pressed
						commandClass.buttonWasPressed();			// execute the command
					}
					break;

				case KeyEvent.VK_RIGHT:

					if (paddle.getX() + Constants.PADDLE_WIDTH < Constants.BOARD_LENGTH) {
						commandClass.setCommand((paddleRightMoveCommand));
						commandClass.buttonWasPressed();
					}
					break;
			}
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	}

	/** This function play sounds on different events in the game */
	private void playSound(String fileName) {
		AudioClip clip = Applet.newAudioClip(getClass().getResource(fileName));
		clip.play();
	}

	/** This function implements the logic to detect the collision between
	 * objects during the play of the game */
	private void checkCollision() {

		if (daemon.isRunning()) {
			// Check the collision between left and right walls

			if (ball.getX() <= Constants.BOARD_OFFSET || (ball.getX() + Constants.BALL_SIZE) > (Constants.BOARD_LENGTH)) {
				ball.setVelocityX(-1 * ball.getVelocityX());
			}

			// Checking for collision with the top wall

			if (ball.getY() <= Constants.BOARD_OFFSET) {
				ball.setVelocityY(-1 * ball.getVelocityY());
			}

			// Game over logic

			if (ball.getY() > Constants.WINDOW_WIDTH) {
				playSound("smb_gameover.wav");
				daemon.stop();
				JOptionPane.showMessageDialog(null, "Game Over\nScore:" + score);

			}

			// Check to see if ball touches the paddle

			if (((ball.getX() + Constants.BALL_SIZE) >= paddle.getX())
					&& (ball.getX() <= paddle.getX() + Constants.PADDLE_WIDTH)) {
				if (((ball.getY() + Constants.BALL_SIZE) >= paddle.getY())
						&& ((ball.getY() + Constants.BALL_SIZE) <= (paddle.getY() + Constants.PADDLE_WIDTH))) {
					ball.setVelocityY(-1 * ball.getVelocityY());

					playSound("bounce.au");
				}
			}

			// Check for collision with bricks

			if (brickCount > 0) {
				int count;

				for (count = Constants.BRICK_COUNT - 1; count >= 0; count--) {
					if (!bricks[count].isHit()) {
						if ((ball.getX() <= (bricks[count].getX() + Constants.BRICK_LENGTH))
								&& (ball.getX() + Constants.BALL_SIZE) >= bricks[count].getX()) {

							if ((ball.getY() <= (bricks[count].getY() + Constants.BRICK_WIDTH))
									&& ((ball.getY() + Constants.BALL_SIZE) >= bricks[count].getY())) {

								score = score + 10;
								scorePanel.setScore("" + score);
								playSound("Brick.au");

								// Check for lateral collision with bricks
								if (((ball.getX() + Constants.BALL_SIZE - ball.getVelocityX()) <= bricks[count].getX())
										|| ((ball.getX() - ball.getVelocityX()) >= (bricks[count].getX() + Constants.BRICK_LENGTH))) {
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
				playSound("applause.wav");
				daemon.stop();
				JOptionPane.showMessageDialog(null, "Congratulations\nScore:" + score);
			}
		}
	}
}
