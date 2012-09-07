package shenhan.p532.a2.model;

import acm.graphics.GRect;
import java.awt.Color;
import shenhan.p532.a2.controller.GameController;
import shenhan.p532.a2.shapes.MyCompoundShapes;

/**
 *
 * @author han
 */
public class Brick extends GameObject {

    private GameController gameController;
    private MyCompoundShapes shapes;
    private BonusBall bonusBall;

    public Brick(GameController gameController, Color color) {
        this.gameController = gameController;
        shapes = new MyCompoundShapes(this);

        GRect rect = new GRect(50, 15);
        this.setBounds(0, 0, 50, 15);

        rect.setFillColor(color);
        rect.setFilled(true);
        shapes.add(rect);

        this.gameController.add(shapes);
    }

    @Override
    public void updateDisplay() {
        if (isVisible()) {
            gameController.add(shapes);
            shapes.setLocation(getPosition()[0], getPosition()[1]);

        } else {
            gameController.remove(shapes);

        }
    }

    @Override
    public void clear() {
        gameController.remove(shapes);
    }

    @Override
    public void sendToBack() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BonusBall getBonusBall() {
        return bonusBall;
    }

    public void setBonusBall(BonusBall bonusBall) {
        this.bonusBall = bonusBall;
    }
}
