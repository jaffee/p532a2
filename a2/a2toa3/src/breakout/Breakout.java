package breakout;


import java.awt.event.KeyListener;
import java.util.ArrayList;

import breakout.commands.CommandGroup;

public class Breakout  {
	private ArrayList<Moveable> moveables; //moveables need to be passed to execute so that objects can do collision detection against them
	private DrawGroup drawables; //drawables will be looped through and drawn after each main game loop iteration
	private ArrayList<CommandGroup> commandGroupStack; // list of all command groups for undo and replay
	private CommandGroup commands; //current working group of commands for a single loop iteration
	private Board board; //JPanel and JFrame are in here.
	
	public static boolean undoPressed = false;
	public static boolean resetPressed = false;
	public static boolean gamePaused = true;
	public static boolean replayPressed = false;
	//public static boolean restartPressed = false;
	private int undoFrames = 30;

	protected static final int TIMER_DELAY = 20;
	
	
	public Breakout(Board board){
		moveables = new ArrayList<Moveable>();
		drawables = new DrawGroup();
		commands = new CommandGroup();
		commandGroupStack = new ArrayList<CommandGroup>();
		commandGroupStack.add(commands);
		this.board = board;
		//board.getFrame().addKeyListener(this);
	}
	
	
	public void start(){
		while(true){
			if(undoPressed)
				handleUndo();
			if(resetPressed)
				handleReset();
			if(gamePaused){
				sleepForDelay();
				continue;
			}
			if(replayPressed){
				handleReplay();
			}
			commands.execute();
			commandGroupStack.add(commands);
			commands = (CommandGroup) commandGroupStack.get(commandGroupStack.size()-1).getCopy();
			drawables.draw(board.getCanvas());
			board.getGamePanel().repaint();
			sleepForDelay();
		}
	}


	private void handleUndo(){
		undoPressed=false;
		int undoFrames;
		int stackSize = commandGroupStack.size();
		if(stackSize<=this.undoFrames)
			undoFrames = stackSize - 1;
		else
			undoFrames = this.undoFrames;
		int i;
		for(i = stackSize -1; i >= stackSize - undoFrames; i--){
			commandGroupStack.remove(i);
		}
		commands = commandGroupStack.get(i);
		commands.undo();
		//this.board.getFrame().addKeyListener(this);
	}
	
	private void handleReset(){
		resetPressed=false;
		int i = commandGroupStack.size() - 1;
		for (; i>0; i--){
			commandGroupStack.remove(i);
		}
		commands=commandGroupStack.get(0);
		commands.undo();
	}
	
	private void handleReplay(){
		Breakout.replayPressed = false;
		for(CommandGroup cg : commandGroupStack){
			while(gamePaused){
				if(resetPressed)
					return;
				sleepForDelay();
			}
			if(resetPressed)
				return;
			cg.undo();
			drawables.draw(board.getCanvas());
			board.getGamePanel().repaint();
			sleepForDelay();
		}
	}
	
	private void sleepForDelay(){
		try{
			Thread.sleep(Breakout.TIMER_DELAY);
		}
		catch(InterruptedException e){
			System.out.println(e);
		}
	}
	
	public void registerMoveable(Moveable m){
		this.moveables.add(m);
	}
	
	public void registerDrawable(Drawable d){
		this.drawables.add(d);
		drawables.draw(this.board.getCanvas());
	}
	
	public void unregisterMoveable(Moveable m){
		this.moveables.remove(m);
	}
	
	public void unregisterDrawable(Drawable d){
		this.drawables.remove(d);
	}
	
	
	public void registerCommand(Commandable command){
		this.commands.addCommand(command);
	}
	public void unregisterCommand(Commandable command){
		this.commands.removeCommand(command);
	}
	
	
	public void registerKeyListener(KeyListener l){
		this.board.getFrame().addKeyListener(l);
	}
	public void unregisterKeyListener(KeyListener l){
		this.board.getFrame().removeKeyListener(l);
	}
	
	
	public ArrayList<Moveable> getMoveables(){
		return this.moveables;
	}

}

