package edu.indiana.cs.p532;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.Random;

/**
 * @author minglu
 * @version 1.1
 * @date 06/09/2012
 */

public class GameBoard implements ActionListener,KeyListener{

	private JFrame mainScreen;
	private GamePanel gamepanel;
	private JPanel buttons;
	private JButton start,stop,resume,undo,replay;
	private Ball ball = new Ball(100,100,5,10,Color.white);
	private Paddle paddle = new Paddle(250,250,20,150,Color.blue);
	private Brick brick = new Brick(400, 200, 20, 20, Color.red);
	private boolean running = true;

    public GameBoard() {     
        mainScreen = new JFrame();
        
        start=new JButton ("Start");
        stop=new JButton ("Stop");
        resume=new JButton ("Resume");
        undo=new JButton ("Undo");
        replay=new JButton ("Replay");
        
        start.addActionListener(this);
        stop.addActionListener(this);
        resume.addActionListener(this);
        undo.addActionListener(this);
        replay.addActionListener(this);
        
        buttons = new JPanel();
        buttons.add(start);
        buttons.add(stop);
        buttons.add(resume);
        buttons.add(undo);
        buttons.add(replay);
        
        gamepanel = new GamePanel(ball, paddle,brick);
        gamepanel.addKeyListener(this);
        gamepanel.setLocation(50, 50);

        mainScreen.getContentPane().add(gamepanel);
        mainScreen.getContentPane().add(buttons);
        mainScreen.setSize(700,700);
        mainScreen.setTitle("Ball Game");
        mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScreen.setResizable(false);      
        mainScreen.setLocationRelativeTo(null);
        mainScreen.setVisible(true);   
        gamepanel.requestFocusInWindow();
    }

    public static void main(String[] args) {
        GameBoard game = new GameBoard();
        game.gameLoop();
    }
    
    public void gameLoop(){
    	while(running){
			gamepanel.repaint();
			try{
				 Command BallMove = new BallMove (ball, gamepanel.getSize());
				 Command PaddleMove = new PaddleMove (paddle, gamepanel.getSize());
				 Command BrickAction = new BrickAction (brick, gamepanel.getSize());
				//logic for undo---if--else
				BallMove.execute();				
				PaddleMove.execute();
				
				ball.checkBounce(paddle);
				if(ball.checkBrick(brick)){
					BrickAction.execute();
					stop();
				}				
				Thread.sleep(20);
			} catch (InterruptedException ex) {
				System.out.print(ex.getStackTrace());
			}			
		}
    	
    }

    public void stop(){
		running=false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 if (e.getSource() == start){
			 System.out.print("Start\n");
			 gamepanel.requestFocusInWindow();

		 }
		 if (e.getSource() == stop){
			 System.out.print("Stop\n");
			 gamepanel.requestFocusInWindow();
		 }
		 if (e.getSource() == resume){
			 System.out.print("Resume\n");
			 gamepanel.requestFocusInWindow();
		 }
		 if (e.getSource() == undo){
			 System.out.print("undo\n");
			 gamepanel.requestFocusInWindow();
		 }
		 if (e.getSource() == replay){
			 System.out.print("replay\n");
			 gamepanel.requestFocusInWindow();
		 }
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP)
			paddle.setUp(true);
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			paddle.setDown(true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP)
			paddle.setUp(false);
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			paddle.setDown(false);
	}
}
