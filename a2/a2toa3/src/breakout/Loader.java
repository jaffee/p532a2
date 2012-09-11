package breakout;

import java.util.Random;

import breakout.commands.BallMoveCommand;
import breakout.commands.BrickMoveCommand;
import breakout.commands.ClockMoveCommand;
import breakout.commands.PaddleMoveCommand;
import breakout.gameObjects.Ball;
import breakout.gameObjects.Brick;
import breakout.gameObjects.Clock;
import breakout.gameObjects.Paddle;

public class Loader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random r = new Random();
		
		Board board = new Board();
		GameController gameController = new GameController(board);
		
		Paddle paddle = new Paddle();
		PaddleMoveCommand paddleMoveCommand = new PaddleMoveCommand(paddle, board.getGamePanel(), gameController.getMoveables()); 
		gameController.registerCommand(paddleMoveCommand);
		gameController.registerDrawable(paddle);
		gameController.registerMoveable(paddle);
		gameController.registerKeyListener(paddle);
		
		Ball ball = new Ball();
		ball.setSpeed(r.nextInt(4)+1, r.nextInt(4)+1);
		BallMoveCommand ballMoveCommand = new BallMoveCommand(ball, board.getGamePanel(), gameController.getMoveables());
		gameController.registerCommand(ballMoveCommand);
		gameController.registerDrawable(ball);
		gameController.registerMoveable(ball);
		
		Brick brick1 = new Brick(50, 50);
		BrickMoveCommand brickMoveCommand = new BrickMoveCommand(brick1, board.getGamePanel(), gameController.getMoveables());
		gameController.registerCommand(brickMoveCommand);
		gameController.registerDrawable(brick1);
		gameController.registerMoveable(brick1);
		
		Clock clock = new Clock();
		ClockMoveCommand clockMoveCommand = new ClockMoveCommand(clock, board.getGamePanel());
		gameController.registerCommand(clockMoveCommand);
		gameController.registerMoveable(clock);
		gameController.registerDrawable(clock);
		//
		
		gameController.start();
	}

}
