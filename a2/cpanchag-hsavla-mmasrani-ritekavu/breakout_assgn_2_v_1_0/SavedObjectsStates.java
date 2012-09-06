package breakout_assgn_2_v_1_0;

public class SavedObjectsStates {

	private Command ballMoveCommand;
	

	private Command paddleMoveCommand;
	private boolean isBrickHit[];

	public boolean[] getIsBrickHit() {
		return isBrickHit;
	}

	public void setIsBrickHit(boolean[] isBrickHit) {
		this.isBrickHit = isBrickHit;
	}

	public SavedObjectsStates(Command ballMoveCommand, Command paddleMoveCommand,
			boolean[] brickArray) {

		this.ballMoveCommand = ballMoveCommand;
		this.paddleMoveCommand = paddleMoveCommand;
		
		
		isBrickHit = new boolean[Constants.BRICK_COUNT];

		for (int i = 0; i < Constants.BRICK_COUNT; i++)
			isBrickHit[i] = brickArray[i];
	}

	public Command getBallMoveCommand() {
		return ballMoveCommand;
	}

	public void setBallMoveCommand(Command ballMoveCommand) {
		this.ballMoveCommand = ballMoveCommand;
	}

	public Command getPaddleMoveCommand() {
		return paddleMoveCommand;
	}

	public void setPaddleMoveCommand(Command paddleMoveCommand) {
		this.paddleMoveCommand = paddleMoveCommand;
	}
	

	
}