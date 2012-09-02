package breakout;

import java.util.ArrayList;

public class CommandGroup implements Commandable {
	private ArrayList<Commandable> commands;
	public CommandGroup(){
		commands = new ArrayList<Commandable>();
	}
	public CommandGroup(ArrayList<Commandable> commands){
		this.commands = commands;
	}
	public Commandable getCopy(){
		CommandGroup copy = new CommandGroup();
		for(Commandable c : commands){
			copy.addCommand(c.getCopy());
		}
		return copy;
	}
	public void execute(ArrayList<Moveable> moveables){
		for(Commandable c : commands){
			c.execute(moveables);
		}
	}
	public void undo(){
		for(int i=0; i<commands.size(); i++){
			commands.get(i).undo();
		}
	}
	public void addCommand(Commandable command){
		commands.add(command);
	}
	public void removeCommand(Commandable command){
		commands.remove(command);
	}

}
