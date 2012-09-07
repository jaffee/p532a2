package shenhan.p532.a2.actions;

import shenhan.p532.a2.model.GameObject;
import shenhan.p532.a2.model.Ball;
import shenhan.p532.a2.model.Brick;
import shenhan.p532.a2.model.Paddle;
import shenhan.p532.a2.model.BonusBall;
import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.util.MediaTools;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import shenhan.p532.a2.controller.GameController;
import shenhan.p532.a2.shapes.MyShape;

/**
 *
 * @author han
 */
public class ProceedGameFrame implements ActionListener {

    private List<GameObject> gameObjects;
    private GameController gameController;
    private AudioClip bounceSound;
    private AudioClip bonusSound;

    public ProceedGameFrame(GameController gameController) {
        this.gameObjects = gameController.getGameObjects();
        this.gameController = gameController;

        try {
            bounceSound = MediaTools.loadAudioClip("bounce.au");
            bonusSound = MediaTools.loadAudioClip("fire.au");
        } catch (Exception e) {
            bounceSound = null;
            bonusSound = null;
            System.err.println(e.toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        for (GameObject gameObject : gameObjects) {
            gameObject.updateDisplay();
            gameObject.pushNewState();
        }
        checkCollision();
    }

    private void checkCollision() {
        checkWallCollision();
        checkBallCollision();
        checkBonusCollision();
    }

    private void checkWallCollision() {
        boolean[] hitWall = detectCollidingWall(GameController.GAME_AREA_TOPLEFT_X, GameController.GAME_AREA_TOPLEFT_Y, GameController.GAME_AREA_RIGHTBOTTOM_X, GameController.GAME_AREA_RIGHTBOTTOM_Y);
        if (hitWall[1]) {
            gameController.pauseGame();
            gameController.setCurrentGameState(GameController.GAME_STATE_ENDED);
            gameController.displayMessage("Game Over!!..Click Start to restart the Game!!");
            gameController.clearGame();
            gameController.getControlPanel().reset();
            gameController.getControlPanel().disableExceptStart();
            return;
        }
        if (hitWall[0]) {
            playBounceSound();
        }
    }

    private void checkBallCollision() {
        MyShape myShape = gameController.getGameBall().getCollidingGameShape();

        if (myShape == null) {
            return;
        }

        //System.out.println(myShape);

        if (myShape.getOwner() instanceof Brick) {
            Brick brick = (Brick) myShape.getOwner();

            Ball gameBall = gameController.getGameBall();

            brick.setVisible(false);
            //gameController.remove((GObject) myShape);

            gameBall.setDxDy(gameBall.getDxDy()[0], -gameBall.getDxDy()[1]);
            gameController.setBrickNumber(gameController.getBrickNumber() - 1);

            if (gameController.getBrickNumber() == 0) {
                gameController.pauseGame();
                gameController.displayMessage("Yon Won! Click Start to play again!");
                gameController.setCurrentGameState(GameController.GAME_STATE_ENDED);
                gameController.clearGame();
                gameController.getControlPanel().reset();
                gameController.getControlPanel().disableExceptStart();
            } else {
                if (brick.getBonusBall() != null) {
                    brick.getBonusBall().setVisible(true);
                    brick.getBonusBall().setDxDy(0, 2);
                }
            }

            playBounceSound();
        } else if (myShape.getOwner() instanceof Paddle) {
            Ball gameBall = gameController.getGameBall();

            GPoint difBetween = differenceBetweenPoints(gameBall.getPosition(), myShape.getOwner().getPosition());

            double newVX = (difBetween.getX() - (gameBall.getBounds()[3] / 2)) / 2;
            double newVY = -(difBetween.getY() / gameBall.getDxDy()[1]);

            if (newVY == 0.0) {
                newVY = 1.0;
            }

            newVY = -newVY;
            gameBall.setDxDy(newVX, newVY);

            playBounceSound();
        }
    }

    private void checkBonusCollision() {
        for (BonusBall bonus : gameController.getBonusBalls()) {
            if (!bonus.isVisible()) {
                continue;
            }

            MyShape myShape = bonus.getCollidingGameShape();

            if (myShape == null) {
                return;
            }
            
            System.out.println(bonus);

            if (myShape.getOwner() instanceof Paddle) {
                System.out.println(myShape + " " + myShape.getOwner());
                
                bonus.setVisible(false);
                int[] bounds = myShape.getOwner().getBounds();
                myShape.getOwner().setBounds(bounds[0], bounds[1], bounds[2] + 40, bounds[3]);
                playBonusSound();
            }

            
        }
    }

    private GPoint differenceBetweenPoints(int[] p1, int[] p2) {
        return new GPoint(p1[0] - p2[0], p1[1] - p2[1]);
    }

    private boolean[] detectCollidingWall(int leftTopX, int leftTopY, int rightBottomX, int rightBottomY) {
        Ball gameBall = gameController.getGameBall();

        boolean hitWall = false;
        boolean gameEnd = false;
        if (gameBall.getPosition()[0] >= rightBottomX) {
            hitWall = true;
            gameBall.setDxDy(-gameBall.getDxDy()[0], gameBall.getDxDy()[1]);
        } else if (gameBall.getPosition()[0] <= leftTopX) {
            hitWall = true;
            gameBall.setDxDy(-gameBall.getDxDy()[0], gameBall.getDxDy()[1]);
        } else if (gameBall.getPosition()[1] <= leftTopY) {
            hitWall = true;
            gameBall.setDxDy(gameBall.getDxDy()[0], -gameBall.getDxDy()[1]);
        } else if (gameBall.getPosition()[1] >= rightBottomY) {
            hitWall = true;
            gameEnd = true;
        }

        return new boolean[]{hitWall, gameEnd};
    }

    private void playBounceSound() {
        new Thread() {

            @Override
            public void run() {
                if (bounceSound != null) {
                    bounceSound.play();
                }
            }
        }.start();
    }

    private void playBonusSound() {
        new Thread() {

            @Override
            public void run() {
                if (bonusSound != null) {
                    bonusSound.play();
                }
            }
        }.start();
    }
}
