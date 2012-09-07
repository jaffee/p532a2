package shenhan.p532.a2.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import shenhan.p532.a2.controller.GameController;

/**
 *
 * @author han
 */
public class PressReplay implements ActionListener {

    private static GameController gameController;
    private static Timer replayTimer;

    public PressReplay(GameController gameController) {
        PressReplay.gameController = gameController;
    }

    public static void stopReplay() {
        replayTimer.stop();
        replayTimer = null;
        gameController.displayMessage("Replay finished, Click Start to continue.");
        gameController.getControlPanel().enableAll();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        gameController.getControlPanel().disableAll();
        gameController.pauseGame();

        gameController.displayMessage("Replaying...");

        replayTimer = new Timer(GameController.GAME_FRAME_FREQUENCY, new Replay(gameController.getGameObjects()));
        replayTimer.start();

        gameController.getGCanvas().requestFocusInWindow();
    }
}
