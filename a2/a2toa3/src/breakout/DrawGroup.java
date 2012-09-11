package breakout;

import java.util.ArrayList;

import java.awt.Image;

public class DrawGroup implements Drawable {
	private ArrayList<Drawable> drawables;
	public DrawGroup(){
		 drawables = new ArrayList<Drawable>();
	}
	public void draw(Image image){
		for(Drawable d : drawables){
			d.draw(image);
		}
	}
	public void add(Drawable d){
		drawables.add(d);
	}
	public void remove(Drawable d){
		drawables.remove(d);
	}

}
