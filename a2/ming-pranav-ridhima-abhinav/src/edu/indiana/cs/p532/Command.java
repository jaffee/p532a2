package edu.indiana.cs.p532;

/**
 * @author minglu
 * @version 1.0
 * @date 30/08/2012
 */

public interface Command {
	public void execute();
	public void undo();
	public void log();
}
