package breakout;

import java.awt.Dimension;
import java.util.ArrayList;

public interface Commandable {
	public abstract void execute(ArrayList<Moveable> moveables, Dimension boardSize);
	public abstract void undo();
	public abstract Commandable getCopy();
}
