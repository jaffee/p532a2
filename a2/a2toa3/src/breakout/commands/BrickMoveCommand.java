package breakout;

import java.awt.Component;
import java.util.ArrayList;

public class BrickMoveCommand implements Commandable {
	private Component gameBoard;
	private ArrayList<Moveable> moveables;
	private Brick brick;
	private double prevX, prevY;
	private boolean prevState;
	private int prevLife;
	
	public BrickMoveCommand(Brick brick, Component gameBoard, ArrayList<Moveable> moveables){
		this.brick = brick;
		this.gameBoard = gameBoard;
		this.moveables = moveables;
		this.prevX = brick.getX();
		this.prevY = brick.getY();
		this.prevState = brick.getState();
		this.prevLife = brick.getLife();
	}

	@Override
	public void execute() {
		brick.move(moveables, gameBoard.getSize());

	}

	@Override
	public void undo() {
		brick.setX(this.prevX);
		brick.setY(this.prevY);
		brick.setState(this.prevState);
		brick.setLife(this.prevLife);
	}

	@Override
	public Commandable getCopy() {
		return new BrickMoveCommand(brick, gameBoard, moveables);
	}

}
