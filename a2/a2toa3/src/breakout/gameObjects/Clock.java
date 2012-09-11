package breakout.gameObjects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

import breakout.Drawable;
import breakout.GameController;
import breakout.Moveable;


public class Clock implements Moveable, Drawable {
	
	private int clockValue;
	private int mins;
	private int secs;
	private static final Color DEFAULT_COLOR = Color.BLUE;
	private static final int DEFAULTX = 200;
	private static final int DEFAULTY = 5;
	
	public Clock(){
		clockValue = 0;
	}

	@Override
	public void draw(Image image) {
		mins = clockValue / 1000  / 60;
		secs = clockValue / 1000 % 60;
		Graphics2D g2D = (Graphics2D) image.getGraphics();
		g2D.setColor(DEFAULT_COLOR);
		String family = "Serif";
	    int style = Font.BOLD;
	    int size = 15;
	    Font font = new Font(family, style, size);
	    g2D.setFont(font);
	    String secsVal = secs < 10 ? "0"+secs : String.valueOf(secs);
	    String minsVal = mins < 10 ? "0"+mins : String.valueOf(mins);
	    int x = DEFAULTX;
	    int y = DEFAULTY;
	    FontMetrics fontMetrics = g2D.getFontMetrics();
	    g2D.drawString(minsVal+":"+secsVal, x, y+fontMetrics.getAscent());

	}

	@Override
	public void move(ArrayList<Moveable> moveables, Dimension boardSize) {
		clockValue += GameController.TIMER_DELAY;
	}

	@Override
	public RectangularShape getBounds() {
		return new Rectangle2D.Double(0, 0, 0, 0);
	}

	public int getClockValue(){
		return this.clockValue;
	}
	
	public void setClockValue(int val){
		this.clockValue = val;
	}

}
