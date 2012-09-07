package shenhan.p532.a2.gui;

import javax.swing.JButton;
import shenhan.p532.a2.controller.GameController;
import shenhan.p532.a2.actions.PressStart;

/**
 *
 * @author han
 */
public class ButtonStart extends JButton {

    public ButtonStart(GameController gameController) {
        super();
        this.setText("Start");
        this.setBounds(190, 10, 80, 20);
        this.addActionListener(new PressStart(gameController));
    }
}
