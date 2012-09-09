package breakout;

import java.util.Random;

public class Loader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random r = new Random();
		
		Board board = new Board();
		Breakout breakout = new Breakout(board);
		
		Paddle paddle = new Paddle();
		PaddleMoveCommand paddleMoveCommand = new PaddleMoveCommand(paddle, board.getGamePanel(), breakout.getMoveables()); 
		breakout.registerCommand(paddleMoveCommand);
		breakout.registerDrawable(paddle);
		breakout.registerMoveable(paddle);
		breakout.registerKeyListener(paddle);
		
		Ball ball = new Ball();
		ball.setSpeed(r.nextInt(4)+1, r.nextInt(4)+1);
		BallMoveCommand ballMoveCommand = new BallMoveCommand(ball, board.getGamePanel(), breakout.getMoveables());
		breakout.registerCommand(ballMoveCommand);
		breakout.registerDrawable(ball);
		breakout.registerMoveable(ball);
		
		Brick brick1 = new Brick(50, 50);
		BrickMoveCommand brickMoveCommand = new BrickMoveCommand(brick1, board.getGamePanel(), breakout.getMoveables());
		breakout.registerCommand(brickMoveCommand);
		breakout.registerDrawable(brick1);
		breakout.registerMoveable(brick1);
		
		Clock clock = new Clock();
		ClockMoveCommand clockMoveCommand = new ClockMoveCommand(clock, board.getGamePanel());
		breakout.registerCommand(clockMoveCommand);
		breakout.registerMoveable(clock);
		breakout.registerDrawable(clock);
		//
		
		breakout.start();
	}

}
