package breakout;

import java.awt.Rectangle;

public class Brick extends Rectangle{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int life = 1; //number of hits the brick can take
	public Brick(int x, int y, int w, int h){
		super(x, y, w, h);
	}
	public Brick(int w, int h){
		super(w, h);
	}
	
	
	public void dec_life(){
		if(this.life>0){
			this.life--;
		}
	}
	public int get_life(){
		return this.life;
	}
}
