package shenhan.p532.a2.model;

import acm.graphics.*;
import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import shenhan.p532.a2.controller.GameController;
import shenhan.p532.a2.shapes.MyCompoundShapes;
import shenhan.p532.a2.shapes.MyShape;

/**
 *
 * @author han
 */
public class BonusBall extends GameObject {

    private double dx;
    private double dy;
    private MyCompoundShapes shapes;
    private GameController gameController;

    public BonusBall(GameController gameController) {
        this.gameController = gameController;
        shapes = new MyCompoundShapes(this);

        GOval oval1 = new GOval(5, 5, 10, 10);
        GOval oval2 = new GOval(0, 0, 20, 20);
        this.setBounds(0, 0, 20, 20);

        oval1.setColor(Color.green);
        oval1.setFilled(true);
        oval2.setColor(Color.red);
        oval2.setFilled(true);
        shapes.add(oval2);
        shapes.add(oval1);

        this.gameController.add(shapes);
    }

    public void setDxDy(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public double[] getDxDy() {
        return new double[]{dx, dy};
    }

    @Override
    public void updateDisplay() {
        if (isVisible()) {
            gameController.add(shapes);

            shapes.setLocation(getPosition()[0] + dx, getPosition()[1] + dy);
            GPoint newPosition = shapes.getLocation();
            setPosition((int) newPosition.getX(), (int) newPosition.getY());
            sendToBack();
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
        shapes.sendToBack();
    }

    public MyShape getCollidingGameShape() {
        GObject cobject;

        List<Point> checkingPoints = new LinkedList<Point>();
        checkingPoints.add(new Point(0, 0));
        checkingPoints.add(new Point(0, getBounds()[3]));
        checkingPoints.add(new Point(getBounds()[2], 0));
        checkingPoints.add(new Point(getBounds()[2], getBounds()[3]));


        for (Point checkingPoint : checkingPoints) {
            cobject = gameController.getElementAt(getPosition()[0] + checkingPoint.getX(), getPosition()[1] + checkingPoint.getY());
            if (cobject != null) {
                if (cobject instanceof GLabel) {
                    return null;
                } else if (cobject instanceof GLine) {
                    return null;
                }
                MyShape myShape = (MyShape) cobject;
                return myShape;
            }
        }
        return null;
    }
}
