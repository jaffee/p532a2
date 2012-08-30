package breakout;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int dy;
	public int screen_height;
	private double springFactor = 1; //how much does it change the speed of the ball
	
	public Paddle(int x, int y, int w, int h){
		super(x, y, w, h);
	}
	public Paddle(int w, int h){
		super(w, h);
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP) {
			
			if(this.y<0)
				dy=0;
			else
				dy = -1;
				
        }
		if (key == KeyEvent.VK_DOWN) {
            
			if(this.y+this.height>screen_height)
				dy=0;
			else
				dy = 1;
        }
	}
	public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
		
	}
	public void move(){
		
		this.y+=this.dy;
		if(this.y<=0)
			this.y=0;
		if(this.y+this.height>=screen_height)
			this.y=screen_height-this.height;
		
	}
	public void set_springFactor(double sf){
		this.springFactor = sf;
	}
	public double get_springFactor(){
		return this.springFactor;
	}
}
