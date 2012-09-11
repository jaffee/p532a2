package breakout;


public interface Commandable {
	public abstract void execute();
	public abstract void undo();
	public abstract Commandable getCopy();
}
