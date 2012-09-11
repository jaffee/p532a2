package breakout.commands;

import java.awt.Component;

import breakout.Commandable;
import breakout.gameObjects.Clock;

public class ClockMoveCommand implements Commandable {
	private Component gameBoard;
	private Clock clock;
	private int prevClockValue;

	public ClockMoveCommand(Clock clock, Component gameBoard) {
		this.gameBoard = gameBoard;
		this.clock = clock;
		this.prevClockValue = clock.getClockValue();
	}

	@Override
	public void execute() {
		clock.move(null, gameBoard.getSize());
	}

	@Override
	public void undo() {
		clock.setClockValue(prevClockValue);
	}

	@Override
	public Commandable getCopy() {
		return new ClockMoveCommand(this.clock, this.gameBoard);
	}

}
