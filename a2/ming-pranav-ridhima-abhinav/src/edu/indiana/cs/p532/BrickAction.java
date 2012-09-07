package edu.indiana.cs.p532;

import java.awt.Dimension;

/**
 * @author minglu
 * @version 1.0
 * @date 30/08/2012
 */

public class BrickAction implements Command {

	private Brick brick;
	private Dimension dim;
	
	public BrickAction (Brick brick, Dimension dim){
		this.brick=brick;
		this.dim =dim;
	}
	
	@Override
	public void execute() {
		brick.update(dim);

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
