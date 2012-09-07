package breakout_assgn_2_v_1_0;

public class CommandClass {

	private Command command;

	public Command getCommand() {
		return command;
	}

	protected void setCommand(Command command) {
		this.command = command;
	}
	
	protected void buttonWasPressed() {
		command.execute();
	}
}
