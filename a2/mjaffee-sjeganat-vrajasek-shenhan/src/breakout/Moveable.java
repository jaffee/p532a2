package breakout;

import java.awt.geom.RectangularShape;
import java.util.ArrayList; //these don't need to be array lists.... just going with stuff I've heard of

public interface Moveable {
	public abstract void move(ArrayList<Moveable> m);
	public abstract RectangularShape getBounds();
}
