package breakout;

import java.awt.Component;
import java.util.ArrayList;

public class BallMoveCommand implements Commandable {
	private Component gameBoard;
	private ArrayList<Moveable> moveables;
	private Ball ball;
	private double prevX, prevY;
	private double prevSpeedX, prevSpeedY;
	public BallMoveCommand(Ball ball, Component gameBoard, ArrayList<Moveable> moveables){
		this.ball = ball;
		this.gameBoard = gameBoard;
		this.moveables = moveables;
		this.prevX = ball.getX();
		this.prevY = ball.getY();
		this.prevSpeedX = ball.getSpeedX();
		this.prevSpeedY = ball.getSpeedY();
		
	}
	public Commandable getCopy(){
		return new BallMoveCommand(this.getBall(), this.gameBoard, this.moveables);
	}
	public void execute(){
		this.prevX = ball.getX();
		this.prevY = ball.getY();
		this.prevSpeedX = ball.getSpeedX();
		this.prevSpeedY = ball.getSpeedY();
		ball.move(moveables, gameBoard.getSize());
	}
	public void undo(){
		ball.setX(prevX);
		ball.setY(prevY);
		ball.setSpeedX(prevSpeedX);
		ball.setSpeedY(prevSpeedY);
	}
	public Ball getBall(){
		return this.ball;
	}
}
