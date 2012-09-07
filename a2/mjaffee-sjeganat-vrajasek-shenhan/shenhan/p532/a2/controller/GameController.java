package shenhan.p532.a2.controller;

import shenhan.p532.a2.model.GameObject;
import shenhan.p532.a2.model.Ball;
import shenhan.p532.a2.model.Brick;
import shenhan.p532.a2.model.Paddle;
import shenhan.p532.a2.model.BonusBall;
import shenhan.p532.a2.model.Clock;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;
import javax.swing.Timer;
import shenhan.p532.a2.actions.RemoveFlashInfo;
import shenhan.p532.a2.gui.ButtonPanel;
import shenhan.p532.a2.gui.PanelPaddleControl;

/**
 *
 * @author han
 */
public class GameController extends GraphicsProgram {

    public final static int GAME_FRAME_FREQUENCY = 25;//in milliseconds
    public final static int GAME_UNDO_FRAMES = 60;//discard those frames
    public final static int GAME_STATE_STARTED = 1;
    public final static int GAME_STATE_STOPPED = 2;
    public final static int GAME_STATE_ENDED = 3;
    public final static int GAME_AREA_TOPLEFT_X = 10;
    public final static int GAME_AREA_TOPLEFT_Y = 25;
    public final static int GAME_AREA_RIGHTBOTTOM_X = 790;
    public final static int GAME_AREA_RIGHTBOTTOM_Y = 490;
    private List<GameObject> gameObjects;
    private GLabel flash;
    private GameExecution exec;
    private int currentGameState;
    private Ball gameBall;
    private BonusBall[] bonusBalls;
    private int brickNumber;
    private ButtonPanel controlPanel;
    private RandomGenerator rgen;
    private String paddleControl;

    public GameController() {
        gameObjects = new LinkedList<GameObject>();
        rgen = RandomGenerator.getInstance();
        currentGameState = 0;
    }

    @Override
    public void run() {
        initGame();
    }

    public void initGame() {
        if (currentGameState != GAME_STATE_ENDED) {
            this.setSize(800, 600);

            getGCanvas().setSize(800, 400);
            getGCanvas().setBackground(new Color(198, 226, 255));
        }

        Paddle gamePaddle = new Paddle(this);
        gamePaddle.setPosition(350, 450);
        gamePaddle.updateDisplay();
        gameObjects.add(gamePaddle);

        Clock gameClock = new Clock(this);
        gameClock.setPosition(350, 20);
        gameClock.updateDisplay();
        gameObjects.add(gameClock);

        Brick gameBrick1 = new Brick(this, Color.blue);
        gameBrick1.setPosition(100, 100);
        gameBrick1.updateDisplay();
        gameObjects.add(gameBrick1);

        Brick gameBrick2 = new Brick(this, Color.blue);
        gameBrick2.setPosition(180, 100);
        gameBrick2.updateDisplay();
        gameObjects.add(gameBrick2);

        Brick gameBrick3 = new Brick(this, Color.blue);
        gameBrick3.setPosition(260, 100);
        gameBrick3.updateDisplay();
        gameObjects.add(gameBrick3);

        Brick gameBrick4 = new Brick(this, Color.pink);
        gameBrick4.setPosition(340, 100);
        gameBrick4.updateDisplay();
        gameObjects.add(gameBrick4);

        Brick gameBrick5 = new Brick(this, Color.pink);
        gameBrick5.setPosition(420, 100);
        gameBrick5.updateDisplay();
        gameObjects.add(gameBrick5);

        Brick gameBrick6 = new Brick(this, Color.pink);
        gameBrick6.setPosition(500, 100);
        gameBrick6.updateDisplay();
        gameObjects.add(gameBrick6);

        brickNumber = 6;

        BonusBall gameBonusBall = new BonusBall(this);
        gameBonusBall.setPosition(340, 100);
        gameBonusBall.setVisible(false);
        gameBonusBall.updateDisplay();
        gameObjects.add(gameBonusBall);
        gameBrick4.setBonusBall(gameBonusBall);

        BonusBall gameBonusBall2 = new BonusBall(this);
        gameBonusBall2.setPosition(420, 100);
        gameBonusBall2.setVisible(false);
        gameBonusBall2.updateDisplay();
        gameObjects.add(gameBonusBall2);
        gameBrick5.setBonusBall(gameBonusBall2);

        BonusBall gameBonusBall3 = new BonusBall(this);
        gameBonusBall3.setPosition(500, 100);
        gameBonusBall3.setVisible(false);
        gameBonusBall3.updateDisplay();
        gameObjects.add(gameBonusBall3);
        gameBrick6.setBonusBall(gameBonusBall3);

        bonusBalls = new BonusBall[]{gameBonusBall, gameBonusBall2, gameBonusBall3};

        gameBall = new Ball(this);
        gameBall.setPosition(400, 300);
        int randomDx = rgen.nextInt(-3, 3);
        gameBall.setDxDy(randomDx, 3);
        gameBall.updateDisplay();
        gameObjects.add(gameBall);



        paddleControl = PanelPaddleControl.ACTION_COMMAND_USE_MOUSE;

        if (currentGameState != GAME_STATE_ENDED) {
            initGUI(gamePaddle);
        }

        setCurrentGameState(GAME_STATE_STOPPED);

        exec = new GameExecution(this, gameClock);

        getGCanvas().requestFocusInWindow();

        displayMessage("Click Start to begin.");
    }

    private void initGUI(Paddle paddle) {
        //System.out.println("initGUI");
        GLine wall1 = new GLine(GAME_AREA_TOPLEFT_X, GAME_AREA_TOPLEFT_Y, GAME_AREA_RIGHTBOTTOM_X, GAME_AREA_TOPLEFT_Y);
        add(wall1);
        GLine wall2 = new GLine(GAME_AREA_TOPLEFT_X, GAME_AREA_RIGHTBOTTOM_Y, GAME_AREA_RIGHTBOTTOM_X, GAME_AREA_RIGHTBOTTOM_Y);
        add(wall2);
        GLine wall3 = new GLine(GAME_AREA_TOPLEFT_X, GAME_AREA_TOPLEFT_Y, GAME_AREA_TOPLEFT_X, GAME_AREA_RIGHTBOTTOM_Y);
        add(wall3);
        GLine wall4 = new GLine(790, GAME_AREA_TOPLEFT_Y, GAME_AREA_RIGHTBOTTOM_X, GAME_AREA_RIGHTBOTTOM_Y);
        add(wall4);

        controlPanel = new ButtonPanel(this, paddle);
        getGCanvas().add(controlPanel);
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void startGame() {
        if (currentGameState == GAME_STATE_STARTED) {
            return;
        }

        if (currentGameState == GAME_STATE_ENDED) {
            initGame();
            return;
        }

        controlPanel.enableAll();
        exec.start();
        setCurrentGameState(GAME_STATE_STARTED);
        displayFlashMessage("Game started.", 1000);
    }

    public void pauseGame() {
        exec.stop();
        setCurrentGameState(GAME_STATE_STOPPED);
        displayMessage("Game Paused");
    }

    public void clearGame() {
        for (GameObject gameObject : gameObjects) {
            gameObject.clear();
        }
        gameObjects.clear();
    }

    public void removeMessage() {
        if (flash != null) {
            remove(flash);
            flash = null;
        }
    }

    public void displayMessage(String info) {
        removeMessage();

        flash = new GLabel(info, (getGCanvas().getWidth() / 2 - 250), getGCanvas().getHeight() / 2);
        Font font = new Font("Monospaced", Font.PLAIN, 20);
        flash.setFont(font);
        flash.setColor(Color.red);
        add(flash);
    }

    public void displayFlashMessage(String info, int delay) {
        removeMessage();

        flash = new GLabel(info, (getGCanvas().getWidth() / 2 - (info.length() * 10)), getGCanvas().getHeight() / 2);
        Font font = new Font("Monospaced", Font.PLAIN, 25);
        flash.setFont(font);
        flash.setColor(Color.red);
        add(flash);
        Timer removeFlash = new Timer(delay, new RemoveFlashInfo(this, flash));
        removeFlash.setRepeats(false);
        removeFlash.start();
    }

    public int getCurrentGameState() {
        return currentGameState;
    }

    public void setCurrentGameState(int currentGameState) {
        this.currentGameState = currentGameState;
    }

    public Ball getGameBall() {
        return gameBall;
    }

    public int getBrickNumber() {
        return brickNumber;
    }

    public void setBrickNumber(int brickNumber) {
        this.brickNumber = brickNumber;
    }

    public ButtonPanel getControlPanel() {
        return controlPanel;
    }

    public synchronized void useKeyboard() {
        paddleControl = PanelPaddleControl.ACTION_COMMAND_USE_KB;
    }

    public synchronized void useMouse() {
        paddleControl = PanelPaddleControl.ACTION_COMMAND_USE_MOUSE;
    }

    public String getPaddleControl() {
        return paddleControl;
    }

    public BonusBall[] getBonusBalls() {
        return bonusBalls;
    }
}
