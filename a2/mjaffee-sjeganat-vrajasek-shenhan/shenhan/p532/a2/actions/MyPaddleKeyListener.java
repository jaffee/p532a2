package shenhan.p532.a2.actions;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import shenhan.p532.a2.controller.GameController;
import shenhan.p532.a2.gui.PanelPaddleControl;
import shenhan.p532.a2.model.Paddle;

/**
 *
 * @author han
 */
public class MyPaddleKeyListener implements KeyListener {

    private Paddle paddle;
    private GameController gameController;

    public MyPaddleKeyListener(Paddle paddle, GameController gameController) {
        this.paddle = paddle;
        this.gameController = gameController;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (gameController.getCurrentGameState() != GameController.GAME_STATE_STARTED) {
            return;
        }

        if (!gameController.getPaddleControl().equals(PanelPaddleControl.ACTION_COMMAND_USE_KB)) {
            return;
        }


        if (ke.getKeyCode() == 37) {
            if (paddle.getPosition()[0] - 30 < GameController.GAME_AREA_TOPLEFT_X) {
                return;
            }
            paddle.setPosition(paddle.getPosition()[0] - 10, paddle.getPosition()[1]);
            paddle.updateDisplay();
        } else if (ke.getKeyCode() == 39) {
            if (paddle.getPosition()[0] + 30 > GameController.GAME_AREA_RIGHTBOTTOM_X) {
                return;
            }
            paddle.setPosition(paddle.getPosition()[0] + 10, paddle.getPosition()[1]);
            paddle.updateDisplay();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
}
