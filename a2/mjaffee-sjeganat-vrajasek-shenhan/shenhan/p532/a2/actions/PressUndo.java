package shenhan.p532.a2.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import shenhan.p532.a2.controller.GameController;
import shenhan.p532.a2.model.Ball;
import shenhan.p532.a2.model.Clock;
import shenhan.p532.a2.model.GameObject;

/**
 *
 * @author han
 */
public class PressUndo implements ActionListener {
    
    private List<GameObject> gameObjects;
    private GameController gameController;
    
    public PressUndo(GameController gameController) {
        this.gameObjects = gameController.getGameObjects();
        this.gameController = gameController;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        gameController.pauseGame();
        
        boolean rewinded = true;
        
        for (GameObject gameObject : gameObjects) {
            for (int i = 0; i < GameController.GAME_UNDO_FRAMES; i++) {
                gameObject.popLastState();
            }
        }
        
        for (GameObject gameObject : gameObjects) {
            GameObject lastState = gameObject.popLastState();
            if (lastState != null) {
                if (lastState instanceof Clock) {
                    Clock clock = (Clock) gameObject;
                    Clock oldClock = (Clock) lastState;
                    
                    clock.setClockValue(oldClock.getClockValue());
                    clock.setText(oldClock.getText());
                    
                    gameObject.updateDisplay();
                    continue;
                } else if (lastState instanceof Ball) {
                    Ball ball = (Ball) gameObject;
                    Ball oldBall = (Ball) lastState;
                    
                    ball.setDxDy(oldBall.getDxDy()[0], oldBall.getDxDy()[1]);
                }
                gameObject.setPosition(lastState.getPosition()[0], lastState.getPosition()[1]);
                gameObject.setVisible(lastState.isVisible());
                gameObject.updateDisplay();
            } else {
                rewinded = false;
            }
        }
        
        if (rewinded) {
            int seconds = GameController.GAME_FRAME_FREQUENCY * GameController.GAME_UNDO_FRAMES / 1000;
            gameController.displayMessage("Game rewinded for " + seconds + " seconds, click start to continue.");
        } else {
            gameController.displayMessage("Cannot undo anymore!");
        }
        
        gameController.getGCanvas().requestFocusInWindow();
    }
}
