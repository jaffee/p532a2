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
public class Ball extends GameObject {

    private double dx;
    private double dy;
    private MyCompoundShapes shapes;
    private GameController gameController;

    public Ball(GameController gameController) {
        this.gameController = gameController;
        shapes = new MyCompoundShapes(this);

        GOval oval = new GOval(10, 10);
        this.setBounds(0, 0, 10, 10);

        oval.setFillColor(Color.black);
        oval.setFilled(true);
        shapes.add(oval);

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
        shapes.setLocation(getPosition()[0] + dx, getPosition()[1] + dy);
        GPoint newPosition = shapes.getLocation();
        setPosition((int) newPosition.getX(), (int) newPosition.getY());
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
                //System.out.println("get: " + myShape + "\nfrom:" + myShape.getOwner());
                return myShape;
            }
        }
        return null;
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
