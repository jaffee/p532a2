package breakout.commands;

import java.awt.Component;
import java.util.ArrayList;

import breakout.Commandable;
import breakout.Moveable;
import breakout.gameObjects.Paddle;

public class PaddleMoveCommand implements Commandable {
	private Paddle paddle;
	private Component gameBoard;
	private ArrayList<Moveable> moveables;
	private double prev_x, prev_y;
	
	
	public PaddleMoveCommand(Paddle paddle, Component gameBoard, ArrayList<Moveable> moveables){
		this.paddle = paddle;
		this.gameBoard = gameBoard;
		this.moveables = moveables;
		this.prev_x = paddle.getX();
		this.prev_y = paddle.getY();
	}
	
	public Commandable getCopy(){
		return new PaddleMoveCommand(this.getPaddle(), this.gameBoard, this.moveables);
	}
	
	public void execute(){
		this.prev_x = paddle.getX();
		this.prev_y = paddle.getY();
		paddle.move(moveables, gameBoard.getSize());
	}
	
	public void undo(){
		paddle.setX(prev_x);
		paddle.setY(prev_y);
	}
	
	public Paddle getPaddle(){
		return this.paddle;
	}
}
