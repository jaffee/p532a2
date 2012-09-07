package shenhan.p532.a2.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import shenhan.p532.a2.controller.GameController;
import shenhan.p532.a2.gui.PanelPaddleControl;

/**
 *
 * @author han
 */
public class ChoosePaddleControl implements ActionListener {

    private GameController gameController;

    public ChoosePaddleControl(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals(PanelPaddleControl.ACTION_COMMAND_USE_KB)) {
            if (gameController.getCurrentGameState() == GameController.GAME_STATE_STARTED) {
                gameController.pauseGame();
            }
            gameController.useKeyboard();
        } else if (ae.getActionCommand().equals(PanelPaddleControl.ACTION_COMMAND_USE_MOUSE)) {
            if (gameController.getCurrentGameState() == GameController.GAME_STATE_STARTED) {
                gameController.pauseGame();
            }
            gameController.useMouse();
        }
        gameController.getGCanvas().requestFocusInWindow();
    }
}
