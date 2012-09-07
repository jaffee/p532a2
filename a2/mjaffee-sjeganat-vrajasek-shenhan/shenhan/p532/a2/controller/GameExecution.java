package shenhan.p532.a2.controller;

import javax.swing.Timer;
import shenhan.p532.a2.actions.ProceedGameFrame;
import shenhan.p532.a2.actions.TickClock;
import shenhan.p532.a2.model.Clock;

/**
 * The execution procedure of the game.
 *
 * @author han
 */
public class GameExecution {

    private Timer timer;
    private Timer clockTimer;
    private GameController gameController;

    public GameExecution(GameController gameController, Clock clock) {
        this.gameController = gameController;
        timer = new Timer(GameController.GAME_FRAME_FREQUENCY, new ProceedGameFrame(this.gameController));
        clockTimer = new Timer(1000, new TickClock(clock));
    }

    public void start() {
        timer.start();
        clockTimer.start();
    }

    public void stop() {
        timer.stop();
        clockTimer.stop();
    }
}
