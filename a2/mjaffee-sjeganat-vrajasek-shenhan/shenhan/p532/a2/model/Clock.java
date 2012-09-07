package shenhan.p532.a2.model;

import acm.graphics.GLabel;
import java.awt.Font;
import shenhan.p532.a2.controller.GameController;

/**
 *
 * @author han
 */
public class Clock extends GameObject {

    private GameController gameController;
    private GLabel shape;
    private int clockValue = 0;

    public Clock(GameController gameController) {
        this.gameController = gameController;

        setText("00:00");
        shape = new GLabel(getText(), 0, 0);
        Font font = new Font("Monospaced", Font.BOLD, 20);
        shape.setFont(font);
        this.setPosition(0, 0);

        this.gameController.add(shape);
    }

    public int getClockValue() {
        return clockValue;
    }

    public void setClockValue(int clockValue) {
        this.clockValue = clockValue;
    }

    @Override
    public void updateDisplay() {
        shape.setLabel(getText());
        shape.setLocation(getPosition()[0], getPosition()[1]);
    }

    @Override
    public void clear() {
        gameController.remove(shape);

    }

    @Override
    public void sendToBack() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
