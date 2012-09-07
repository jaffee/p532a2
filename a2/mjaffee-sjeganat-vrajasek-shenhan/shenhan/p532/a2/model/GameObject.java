package shenhan.p532.a2.model;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author han
 */
public abstract class GameObject implements Cloneable {

    public final static int HISTROY_RECORD_SIZE = 0;
    private int x, y, width, height;
    private List<GameObject> states = new LinkedList<GameObject>();
    private String text;
    private boolean visible = true;

    public synchronized void pushNewState() {
//        if (states.size() > HISTROY_RECORD_SIZE) {
//            states.remove(0);
//        }
        try {
            states.add((GameObject) this.clone());
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(GameObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized GameObject popLastState() {
        if (states.size() > 0) {
            return states.remove(states.size() - 1);
        } else {
            return null;
        }
    }

    /**
     * Top left corner.
     *
     * @param x
     * @param y
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setBounds(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int[] getPosition() {
        return new int[]{x, y};
    }

    public int[] getBounds() {
        return new int[]{x, y, width, height};
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public abstract void updateDisplay();

    public List<GameObject> getStates() {
        return states;
    }

    public abstract void clear();

    public abstract void sendToBack();
}
