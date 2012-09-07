package shenhan.p532.a2.actions;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import shenhan.p532.a2.controller.GameController;
import shenhan.p532.a2.gui.PanelPaddleControl;
import shenhan.p532.a2.model.Paddle;

/**
 *
 * @author han
 */
public class MyPaddleMouseListener implements MouseMotionListener {

    private GameController gameController;
    private Paddle paddle;

    public MyPaddleMouseListener(Paddle paddle, GameController gameController) {
        this.gameController = gameController;
        this.paddle = paddle;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (gameController.getCurrentGameState() != GameController.GAME_STATE_STARTED) {
            return;
        }

        if (!gameController.getPaddleControl().equals(PanelPaddleControl.ACTION_COMMAND_USE_MOUSE)) {
            return;
        }

        Point movePos = e.getPoint();
        if (movePos.x >= (GameController.GAME_AREA_TOPLEFT_X + paddle.getBounds()[2] / 2) && movePos.x <= (GameController.GAME_AREA_RIGHTBOTTOM_X - paddle.getBounds()[2] / 2)) {
            paddle.setPosition(movePos.x - (paddle.getBounds()[2] / 2), paddle.getBounds()[1]);
            paddle.updateDisplay();
        }
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }
}
