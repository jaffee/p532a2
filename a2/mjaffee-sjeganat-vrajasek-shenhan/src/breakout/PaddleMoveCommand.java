package breakout;

import java.awt.Dimension;
import java.util.ArrayList;

public class PaddleMoveCommand implements Commandable {
	private Paddle paddle;
	private double prev_x, prev_y;
	public PaddleMoveCommand(Paddle paddle){
		this.paddle = paddle;
		this.prev_x = paddle.getX();
		this.prev_y = paddle.getY();
	}
	public Commandable getCopy(){
		return new PaddleMoveCommand(this.getPaddle());
	}
	public void execute(ArrayList<Moveable> moveables, Dimension boardSize){
		this.prev_x = paddle.getX();
		this.prev_y = paddle.getY();
		paddle.move(moveables, boardSize);
	}
	public void undo(){
		paddle.setX(prev_x);
		paddle.setY(prev_y);
	}
	public Paddle getPaddle(){
		return this.paddle;
	}
}
