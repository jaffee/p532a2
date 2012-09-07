package shenhan.p532.a2.gui;

import javax.swing.JButton;
import shenhan.p532.a2.controller.GameController;
import shenhan.p532.a2.actions.PressReplay;

/**
 *
 * @author han
 */
public class ButtonReplay extends JButton {

    public ButtonReplay(GameController gameController) {
        super();
        this.setText("Replay");
        this.setBounds(100, 10, 80, 20);
        this.addActionListener(new PressReplay(gameController));
    }
}
