package breakout;
import java.awt.geom.Ellipse2D;

public class Ball extends Ellipse2D.Double{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean onFire = false;
	private int xVelocity = 0;
	private int yVelocity = 0;
	public Ball(double x, double y, int w, int h){
		super(x, y, w, h);
	}
	
	public Ball(){
		super();
	}
	
	public void move(){
		this.x += xVelocity;
		this.y += yVelocity;
	}
	
	public int getXVelocity(){
		return this.xVelocity;
	}
	
	public int getYVelocity(){
		return this.yVelocity;
	}
	
	public void invertXVelocity(){
		this.xVelocity *= -1;
	}
	
	public void invertYVelocity(){
		this.yVelocity *= -1;
	}
	
	public void setXVelocity(int vx){
		this.xVelocity = vx;
	}
	
	public void setYVelocity(int vy){
		this.yVelocity = vy;
	}
	
}
