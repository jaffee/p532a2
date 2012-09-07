package shenhan.p532.a2.gui;

import javax.swing.JButton;
import shenhan.p532.a2.controller.GameController;
import shenhan.p532.a2.actions.PressRestart;

/**
 *
 * @author han
 */
public class ButtonRestart extends JButton {

    public ButtonRestart(GameController gameController) {
        super();
        this.setText("Restart");
        this.setBounds(370, 10, 80, 20);
        this.addActionListener(new PressRestart(gameController));
    }
}
