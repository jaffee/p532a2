package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;


public class Paddle implements Moveable, Drawable {
	private static final int DEFAULT_WIDTH = 50;
	private static final int DEFAULT_HEIGHT = 10;
	private static final int INITIAL_X = 10;
	private static final int INITIAL_Y = 280;
	private static final Color DEFAULT_COLOR = Color.black;
	private Color color;
	private double dx = 0;
	private double dy = 0;
	Rectangle2D.Double paddleShape; //// I'd like this to be more generic, but I'm not sure how
	
	public Paddle(){
		paddleShape = new Rectangle2D.Double( Paddle.INITIAL_X, Paddle.INITIAL_Y, Paddle.DEFAULT_WIDTH, Paddle.DEFAULT_HEIGHT);
		this.color = Paddle.DEFAULT_COLOR;
	}
	
	public void draw(Image image){
		Graphics2D g2D = (Graphics2D) image.getGraphics();
		g2D.setColor(this.color);
		g2D.fill(this.paddleShape);
	}
	public void move(ArrayList<Moveable> moveables){
		double x = this.paddleShape.getX();
		x+=this.dx;
		this.paddleShape.x=x;
		double y = this.paddleShape.getY();
		y+=this.dy;
		this.paddleShape.y=y;
		//needs collision logic
	}
	public double getX(){
		return this.paddleShape.getX();
	}
	public double getY(){
		return this.paddleShape.getY();
	}
	public void setX(double x){
		this.paddleShape.x = x;
	}
	public void setY(double y){
		this.paddleShape.y = y;
	}
	public RectangularShape getBounds(){
		return this.paddleShape.getBounds();
	}
}
