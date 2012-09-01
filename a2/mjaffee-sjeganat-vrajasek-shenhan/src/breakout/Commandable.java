package breakout;

import java.util.ArrayList;

public interface Commandable {
	public abstract void execute(ArrayList<Moveable> moveables);
	public abstract void undo();
	public abstract Commandable getCopy();
}
