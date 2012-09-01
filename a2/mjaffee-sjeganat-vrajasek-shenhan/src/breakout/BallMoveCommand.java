package breakout;

import java.util.ArrayList;

public class BallMoveCommand implements Commandable {
	private Ball ball;
	private double prevX, prevY;
	private double prevSpeedX, prevSpeedY;
	public BallMoveCommand(Ball ball){
		this.ball = ball;
		this.prevX = ball.getX();
		this.prevY = ball.getY();
		this.prevSpeedX = ball.getSpeedX();
		this.prevSpeedY = ball.getSpeedY();
		
	}
	public Commandable getCopy(){
		return new BallMoveCommand(this.getBall());
	}
	public void execute(ArrayList<Moveable> moveables){
		this.prevX = ball.getX();
		this.prevY = ball.getY();
		this.prevSpeedX = ball.getSpeedX();
		this.prevSpeedY = ball.getSpeedY();
		ball.move(moveables);
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
