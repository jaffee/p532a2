package shenhan.p532.a2.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import shenhan.p532.a2.controller.GameController;

/**
 *
 * @author han
 */
public class PressPause implements ActionListener {

    private GameController gameController;

    public PressPause(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        gameController.pauseGame();
        gameController.getGCanvas().requestFocusInWindow();
    }
}
