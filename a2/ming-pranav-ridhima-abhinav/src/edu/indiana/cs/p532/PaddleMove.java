package edu.indiana.cs.p532;

import java.awt.Dimension;

/**
 * @author minglu
 * @version 1.0
 * @date 30/08/2012
 */
public class PaddleMove implements Command {

	private Paddle paddle;
	private Dimension dim;
	
	public PaddleMove (Paddle paddle, Dimension dim){
		this.paddle=paddle;
		this.dim =dim;
	}
	@Override
	public void execute() {
		// Save Paddle Location here
		paddle.update(dim);
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
