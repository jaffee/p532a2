package breakout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// we could consider having a gameboard interface that defines functions like getCanvas() and getSize() 
public class Board implements ActionListener {
	private JFrame frame;
	private GamePanel gamePanel;
	private JPanel buttonsPanel;
	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 410;
	private static final String DEFAULT_TITLE = "Breakout";
	private static final Color DEFAULT_BGCOLOR = Color.GREEN;
	
	
	public Board(){
		this.frame = new JFrame();
		this.gamePanel = new GamePanel();
		this.buttonsPanel = new JPanel();
		frame.setFocusable(true);
		gamePanel.setFocusable(true);
		gamePanel.setBackground(Board.DEFAULT_BGCOLOR);
		gamePanel.setDoubleBuffered(true);
		gamePanel.setSize(Board.DEFAULT_WIDTH, Board.DEFAULT_HEIGHT);
		buttonsPanel.setBackground(Color.gray);
		JButton start = new JButton("START");
		start.addActionListener(this);
		JButton pause = new JButton("PAUSE");
		pause.addActionListener(this);
		JButton restart = new JButton("RESTART");
		restart.addActionListener(this);
		JButton undo = new JButton("UNDO");
		undo.addActionListener(this);
		JButton replay = new JButton("REPLAY");
		replay.addActionListener(this);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 10, 10);
		buttonsPanel.setLayout(flowLayout);
		buttonsPanel.add(start);
		buttonsPanel.add(pause);
		buttonsPanel.add(restart);
		buttonsPanel.add(undo);
		buttonsPanel.add(replay);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(Board.DEFAULT_WIDTH, Board.DEFAULT_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setTitle(Board.DEFAULT_TITLE);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());
		frame.getContentPane().add(this.gamePanel, BorderLayout.CENTER);
		frame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	
	public void setSize(int height, int width){
		this.frame.setSize(height, width);
		this.gamePanel.setSize(height, width);
	}
	
	public void setTitle(String title){
		frame.setTitle(title);
	}
	
	public JFrame getFrame(){
		return this.frame;
	}
	
	public Image getCanvas(){
		return this.gamePanel.getBufferedImage();
	}
	
	public JPanel getGamePanel(){
		return this.gamePanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().contentEquals("START")){
			Breakout.gamePaused = false;
		}
		else if(e.getActionCommand().contentEquals("PAUSE")){
			Breakout.gamePaused = true;
		}
		else if(e.getActionCommand().contentEquals("UNDO")){
			Breakout.undoPressed = true;
		}
		else if(e.getActionCommand().contentEquals("REPLAY")){
			Breakout.replayPressed = true;
		}
		else if(e.getActionCommand().contentEquals("RESTART")){
			Breakout.resetPressed = true;
		}
		
		this.frame.requestFocusInWindow();
	}
	
}
