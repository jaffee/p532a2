package shenhan.p532.a2.gui;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import shenhan.p532.a2.controller.GameController;
import shenhan.p532.a2.model.Paddle;

/**
 *
 * @author han
 */
public class ButtonPanel extends JPanel {

    private List<JButton> buttons;
    private PanelPaddleControl buttonGroupPaddleControl;

    public ButtonPanel(GameController gameController, Paddle paddle) {
        super();
        this.setLayout(null);
        this.setBounds(0, 500, 800, 100);

        ButtonUndo undoButton = new ButtonUndo(gameController);
        this.add(undoButton);

        ButtonReplay replayButton = new ButtonReplay(gameController);
        this.add(replayButton);

        ButtonStart startButton = new ButtonStart(gameController);
        this.add(startButton);

        ButtonPause pauseButton = new ButtonPause(gameController);
        this.add(pauseButton);

        ButtonRestart restartButton = new ButtonRestart(gameController);
        this.add(restartButton);

        buttonGroupPaddleControl = new PanelPaddleControl(gameController, paddle);
        this.add(buttonGroupPaddleControl);

        buttons = new LinkedList<JButton>();
        buttons.add(undoButton);
        buttons.add(replayButton);
        buttons.add(startButton);
        buttons.add(pauseButton);
        buttons.add(restartButton);
    }

    public void disableAll() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
        buttonGroupPaddleControl.disableAll();
    }

    public void enableAll() {
        for (JButton button : buttons) {
            button.setEnabled(true);
        }
        buttonGroupPaddleControl.enableAll();
    }

    public void reset() {
        buttonGroupPaddleControl.reset();
    }

    public void disableExceptStart() {
        for (JButton button : buttons) {
            if (button instanceof ButtonStart) {
                continue;
            }
            button.setEnabled(false);
        }
        buttonGroupPaddleControl.disableAll();
    }
}
