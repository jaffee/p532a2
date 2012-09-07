package edu.indiana.cs.p532;

import java.awt.Dimension;

/**
 * @author minglu
 * @version 1.0
 * @date 30/08/2012
 */

public class BallMove implements Command {

	private Ball ball;
	private Dimension dim;
	
	public BallMove (Ball ball, Dimension dim){
		this.ball=ball;
		this.dim =dim;
	}
	@Override
	public void execute() {
		//Save Location--call ball getX, getY
		ball.update(dim);
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void log() {
		// TODO Auto-generated method stub

	}

}
