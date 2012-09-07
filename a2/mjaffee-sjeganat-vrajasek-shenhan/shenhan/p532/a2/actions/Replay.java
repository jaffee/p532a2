package shenhan.p532.a2.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import shenhan.p532.a2.model.GameObject;

/**
 *
 * @author han
 */
public class Replay implements ActionListener {

    private List<GameObject> gameObjects;
    private int currentFrame = 0;

    public Replay(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (currentFrame == gameObjects.get(0).getStates().size() - 1) {
            PressReplay.stopReplay();
            currentFrame = 0;
            return;
        }

        for (GameObject gameObject : gameObjects) {
            List<GameObject> states = gameObject.getStates();
            if (states.isEmpty()) {
                return;
            }
            GameObject state = states.get(currentFrame);
            state.updateDisplay();
        }

        currentFrame++;
    }
}
