package breakout;

/* much code from http://zetcode.com/tutorials/javagamestutorial/basics/ 
 * and http://zetcode.com/tutorials/javagamestutorial/animation/
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import java.awt.Toolkit;
import java.util.Formatter;

import javax.swing.JPanel;

public class Board extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Thread animator;
	private final int DELAY = 50;
	boolean gameover=false;
	private Paddle paddle;
	private Brick brick;
	private Ball ball;
	private int w=800;
	private int h=560;
	
	
	public Board(){ 
        addKeyListener(new TAdapter());
        setFocusable(true);
		setBackground(Color.black);
		setDoubleBuffered(true);

		int paddlew= 20;
		int paddleh=200;
		int paddlex=(int) (w/2-paddlew/2);
		int paddley=(int) (h/2-paddleh/2);
		   
		  
		paddle = new Paddle(paddlex, paddley, paddlew, paddleh);
		paddle.screen_height=h;
		brick = new Brick(35, 55);
		ball = new Ball();
		ball.width = 20;
		ball.height = 20;
		ball.x = 70;
		ball.y = 30;
		ball.setXVelocity(1);
		ball.setYVelocity(1);
		brick.x = w*3/4;
		brick.y = h/2-brick.height/2;
	}
	
	public void addNotify() {
		super.addNotify();
		animator = new Thread(this);
		animator.start();
	}
	public void paint(Graphics g){
		
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		
	   
//		RenderingHints rh =
//				new RenderingHints(RenderingHints.KEY_ANTIALIASING,
//	                               RenderingHints.VALUE_ANTIALIAS_ON);
//		rh.put(RenderingHints.KEY_RENDERING,
//	             RenderingHints.VALUE_RENDER_QUALITY);
//		g2.setRenderingHints(rh);
	    
	    g2.setStroke(new BasicStroke(1));
	    g2.setColor(Color.gray);
	    g2.fill(ball);
	    g2.setColor(Color.cyan);
	    g2.fill(paddle);
	    g2.setColor(Color.red);
	    if(brick.get_life()>0)
	    	g2.fill(brick);
	    Toolkit.getDefaultToolkit().sync();
	    g.setFont(new Font("Courier New", Font.PLAIN, 55));
	    StringBuilder sb = new StringBuilder();
	      Formatter formatter = new Formatter(sb);
	      if(gameover)
	      {
	    	  formatter.format("Game Over");
	      }
	      //formatter.format("TP: %d Ball @(%3.0f,%3.0f) Speed=(%2.0f,%2.0f) Brick=(%d,%d)", tp, ball.x, ball.y, ball.speedx, ball.speedy, brick.x, brick.y);
	      g.drawString(sb.toString(), w/2 - 150, h/2-22);
        g.dispose();
	}
	public void moveStuff(){
		
		ball.move();
		paddle.move();
		
	}
	public void checkCollisions() throws InterruptedException{
		double ballCenterx = ball.x + ball.width/2;
		double ballCentery = ball.y + ball.height/2;
		if(ball.x>=w||ball.x<=0){
			ball.invertXVelocity();
		}
		if(ball.y+ball.height>=h||ball.y<=0){
			ball.invertYVelocity();
		}
		if(ball.intersects(paddle)){
			if(ballCenterx>=paddle.x&&ballCenterx<=(paddle.x+paddle.width)){
				ball.invertYVelocity();
			}
			else if(ballCentery>=paddle.y&&ballCentery<=(paddle.y+paddle.height)){
				ball.invertXVelocity();
			}
			else {
				ball.invertXVelocity();
				ball.invertYVelocity();
			}
		}
		if(brick.get_life()>0&&ball.intersects(brick)){
			if(ballCenterx>=brick.x&&ballCenterx<=(brick.x+brick.width)){
				ball.invertYVelocity();
			}
			else if(ballCentery>=brick.y&&ballCentery<=(brick.y+brick.height)){
				ball.invertXVelocity();
			}
			else {
				ball.invertXVelocity();
				ball.invertYVelocity();
			}
			
//			Thread.sleep(100);
			brick.dec_life();
			if(brick.get_life()==0)
				gameover=true;
		}
	}
	private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
        }
    }
	public void run(){
		long beforeTime, timeDiff, sleep;
		beforeTime = System.currentTimeMillis();
		while (!gameover){
			try {
				checkCollisions();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			moveStuff();
			repaint();
			
			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY - timeDiff;
			
			if (sleep<0)
				sleep = 2;
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				System.out.println("interrupted");
			}
		}
	}
}
