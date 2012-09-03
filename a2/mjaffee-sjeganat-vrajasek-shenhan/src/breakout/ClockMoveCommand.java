package breakout;

import java.awt.Dimension;
import java.util.ArrayList;

public class ClockMoveCommand implements Commandable {
	
	private Clock clock;
	private int prevClockValue;

	public ClockMoveCommand(Clock clock) {
		this.clock = clock;
		this.prevClockValue = clock.getClockValue();
	}

	@Override
	public void execute(ArrayList<Moveable> moveables, Dimension boardSize) {
		clock.move(moveables, boardSize);

	}

	@Override
	public void undo() {
		clock.setClockValue(prevClockValue);
	}

	@Override
	public Commandable getCopy() {
		return new ClockMoveCommand(this.clock);
	}

}
