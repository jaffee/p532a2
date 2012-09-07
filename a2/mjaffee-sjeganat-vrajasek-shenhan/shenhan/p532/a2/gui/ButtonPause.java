package shenhan.p532.a2.gui;

import javax.swing.JButton;
import shenhan.p532.a2.controller.GameController;
import shenhan.p532.a2.actions.PressPause;

/**
 *
 * @author han
 */
public class ButtonPause extends JButton {

    public ButtonPause(GameController gameController) {
        super();
        this.setText("Pause");
        this.setBounds(280, 10, 80, 20);
        this.addActionListener(new PressPause(gameController));
    }
}
