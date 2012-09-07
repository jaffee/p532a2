package shenhan.p532.a2.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import shenhan.p532.a2.controller.GameController;

/**
 *
 * @author han
 */
public class PressRestart implements ActionListener {

    private GameController gameController;

    public PressRestart(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        gameController.pauseGame();
        gameController.clearGame();
        gameController.setCurrentGameState(GameController.GAME_STATE_ENDED);
        gameController.displayMessage("Click Start to begin.");
        gameController.getControlPanel().reset();
        gameController.getControlPanel().disableExceptStart();
        gameController.getGCanvas().requestFocusInWindow();
    }
}
