package breakout;

public class Loader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board board = new Board();
		Breakout breakout = new Breakout(board);
		
		Paddle paddle = new Paddle();
		PaddleMoveCommand paddleMoveCommand = new PaddleMoveCommand(paddle, board.getGamePanel(), breakout.getMoveables()); 
		breakout.registerCommand(paddleMoveCommand);
		breakout.registerDrawable(paddle);
		breakout.registerMoveable(paddle);
		breakout.registerKeyListener(paddle);
		
		Ball ball = new Ball();
		ball.setSpeed(3, 3);
		BallMoveCommand ballMoveCommand = new BallMoveCommand(ball, board.getGamePanel(), breakout.getMoveables());
		breakout.registerCommand(ballMoveCommand);
		breakout.registerDrawable(ball);
		breakout.registerMoveable(ball);
		
		Clock clock = new Clock();
		ClockMoveCommand clockMoveCommand = new ClockMoveCommand(clock, board.getGamePanel());
		breakout.registerCommand(clockMoveCommand);
		breakout.registerMoveable(clock);
		breakout.registerDrawable(clock);
		//
		
		breakout.start();
	}

}
