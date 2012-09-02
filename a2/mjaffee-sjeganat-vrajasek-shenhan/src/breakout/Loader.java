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
		PaddleMoveCommand paddleMoveCommand = new PaddleMoveCommand(paddle); 
		breakout.registerCommand(paddleMoveCommand);
		breakout.registerDrawable(paddle);
		
		Ball ball = new Ball();
		ball.setSpeed(3, 3);
		BallMoveCommand ballMoveCommand = new BallMoveCommand(ball);
		breakout.registerCommand(ballMoveCommand);
		breakout.registerDrawable(ball);
		
		//
		
		breakout.start();
	}

}
