package edu.indiana.cs.p532;

import java.awt.Dimension;
import java.awt.Graphics;

/**
 * @author minglu
 * @version 1.0
 * @date 30/08/2012
 */

public interface Element {
	public void update(Dimension dim);
	public void draw(Graphics g);
}
