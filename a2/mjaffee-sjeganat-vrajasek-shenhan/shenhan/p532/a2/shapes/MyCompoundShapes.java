package shenhan.p532.a2.shapes;

import acm.graphics.GCompound;
import shenhan.p532.a2.model.GameObject;

/**
 *
 * @author han
 */
public class MyCompoundShapes extends GCompound implements MyShape {

    private GameObject owner;

    public MyCompoundShapes(GameObject owner) {
        super();
        this.owner = owner;

    }

    @Override
    public GameObject getOwner() {
        return owner;
    }
}
