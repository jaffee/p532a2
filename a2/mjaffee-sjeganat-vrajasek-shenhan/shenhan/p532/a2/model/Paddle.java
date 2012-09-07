package shenhan.p532.a2.model;

import acm.graphics.GRoundRect;
import java.awt.Color;
import shenhan.p532.a2.actions.MyPaddleKeyListener;
import shenhan.p532.a2.actions.MyPaddleMouseListener;
import shenhan.p532.a2.controller.GameController;
import shenhan.p532.a2.shapes.MyCompoundShapes;

/**
 *
 * @author han
 */
public class Paddle extends GameObject {

    private GameController gameController;
    private MyCompoundShapes shapes;
    private GRoundRect shape;

    public Paddle(GameController gameController) {
        this.gameController = gameController;
        shapes = new MyCompoundShapes(this);

        shape = new GRoundRect(60, 10);
        this.setBounds(0, 0, 60, 10);

        shape.setFillColor(Color.black);
        shape.setFilled(true);
        shapes.add(shape);

        this.gameController.add(shapes);

        gameController.addKeyListeners(new MyPaddleKeyListener(this, this.gameController));
        gameController.addMouseListeners(new MyPaddleMouseListener(this, this.gameController));
    }

    @Override
    public void updateDisplay() {
        shapes.setLocation(getPosition()[0], getPosition()[1]);
        shape.setSize(getBounds()[2], getBounds()[3]);
    }

    @Override
    public void clear() {
        gameController.remove(shapes);

    }

    @Override
    public void sendToBack() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}