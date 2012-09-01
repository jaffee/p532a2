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
		for(int i=0; i<commands.size(); i++){
			copy.addCommand(commands.get(i).getCopy());
		}
		return copy;
	}
	public void execute(ArrayList<Moveable> moveables){
		for(int i=0; i<commands.size(); i++){
			commands.get(i).execute(moveables);
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
